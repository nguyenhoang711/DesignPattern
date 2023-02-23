//
package com.hoangnguyen.QuanLyDanCu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.hoangnguyen.QuanLyDanCu.utils.DBConnector;

/**
 * This class is . 
 * 
 * @Description: .
 * @author: NguyenHoang
 * @create_date: Dec 29, 2022
 * @version: 1.0
 * @modifer: NguyenHoang
 * @modifer_date: Dec 29, 2022
 */
public class AccountDAO {
	private static Connection conn = null;
	
	//chuyen tu account name sang id
	public static int nameToMaCD(String username) {
		int id = 0;
		String sql = "select id from taikhoan\r\n"
				+ "where username = ?";
		try {
			conn = DBConnector.connect();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				id = res.getInt("id");
			}
			return id;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return id;
	}
	
	public static boolean insertAccountControl(String username, String password, String role) {
		String sql = "INSERT INTO taikhoan(username, pass, `role`) VALUES(?,?,?)";
		int result;
		try {
			conn = DBConnector.connect();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, role);
			result = ps.executeUpdate();
			return result >0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	//lay thong tin ca nhan tu DB
	public static String getAccountInfo(String username) {
		String data = "";
		String sql = "select pass \r\n"
				+ "from taikhoan \r\n"
				+ "where username = ?;";
		try {
			conn = DBConnector.connect();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				data = res.getString("pass");
			}
			return data;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return data;
	}
	
	//update thong tin account
	public static void updateAccount(int id,String role) {
		String sql = "update taikhoan\r\n"
				+ "set `role` = ?\r\n"
				+ "where id = ?";
		try {
			conn = DBConnector.connect();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, role);
			ps.setInt(2, id);
			int result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//update thong tin account
	public static void updateAccountInfo(String uc,String username, String pass) {
		String sql = "UPDATE taikhoan"
				+ "SET username= ?,"
				+ "pass = ?"
				+ "WHERE username = ?";
		try {
			conn = DBConnector.connect();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, pass);
			ps.setString(3, uc);
			int result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//lay ra tat ca tai khoan
		public static ArrayList<String[]> getAllAccount(){
			ArrayList<String[]> arrayList = new ArrayList<String[]>();
			String[] arrayString;
			int columnSize;//kich thuoc cua 1 ban ghi
			//lay thong tin trong bang tablename
			try {
				String sql = "select *\r\n"
						+ "from taikhoan";
				conn = DBConnector.connect();
				Statement stm = conn.createStatement();
				ResultSet res = stm.executeQuery(sql);
				columnSize = res.getMetaData().getColumnCount();
				int i;
				while(res.next()) {
					arrayString = new String[columnSize];
					for(i = 1; i < columnSize + 1; i++) {
						arrayString[i - 1] = res.getString(i);
					}
					arrayList.add(arrayString);
				}
				
				System.out.println("arrayLength = " + arrayList.size());
				System.out.println("column size =" + columnSize);
				DBConnector.disconnect();
				return arrayList;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			return arrayList;
		}
}
