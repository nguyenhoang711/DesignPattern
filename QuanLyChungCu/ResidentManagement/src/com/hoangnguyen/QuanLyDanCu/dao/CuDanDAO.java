//
package com.hoangnguyen.QuanLyDanCu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.hoangnguyen.QuanLyDanCu.entity.CuDan;
import com.hoangnguyen.QuanLyDanCu.entity.Loai;
import com.hoangnguyen.QuanLyDanCu.utils.DBConnector;

/**
 * This class is . 
 * 
 * @Description: .
 * @author: NguyenHoang
 * @create_date: Dec 28, 2022
 * @version: 1.0
 * @modifer: NguyenHoang
 * @modifer_date: Dec 28, 2022
 */
public class CuDanDAO {
	private static Connection conn = null;
	//lay ra ten cu dan chua duoc tao phong
	public static Object[] getAllName(){
		ArrayList<String> names = new ArrayList<String>();
		String sql = "select hoten from cudan\r\n"
				+ "where loai = ''";
		try {
			conn = DBConnector.connect();
			Statement stm = conn.createStatement();
			ResultSet res = stm.executeQuery(sql);
			while(res.next()) {
				names.add(res.getString(1));
			}
			return names.toArray();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return names.toArray();
	}
	
	
	public static ArrayList<String[]> getAllCuDan(){
		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String[] arrayString;
		int columnSize;//kich thuoc cua 1 ban ghi
		//lay thong tin trong bang tablename
		try {
			String sql = "select cudan.*, hopdong.canho_id\r\n"
					+ "from cudan\r\n"
					+ "LEFT JOIN hopdong \r\n"
					+ "ON cudan.id = hopdong.cudan_id;";
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
	
	
	//lay ten cu dan theo so dien thoai
	public static String getCuDanNameBySDT(String sdt) {
		String tam = "";
		try {
			String query = "SELECT hoten FROM cudan WHERE SDT = ?";
			conn = DBConnector.connect();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, sdt);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				tam = res.getString("hoten");
			}
			return tam;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return tam;
	}
	
	
	// them moi cu dan
	public static boolean insertNewCuDan(String ten, String email, String sodt, String quequan,String loai) {
		String sql = "INSERT INTO cudan(hoten, email, sdt,quequan,loai) VALUES(?,?,?,?,?)";
		int result;
		try {
			conn = DBConnector.connect();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ten);
			ps.setString(2, email);
			ps.setString(3, sodt);
			ps.setString(4, quequan);
			ps.setString(5, loai);
			result = ps.executeUpdate();
			return result >0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	//xoa thong tin cu dan
	public static void deleteCuDan(int id) {
		String sql = "DELETE FROM cudan WHERE id = ?";
		try {
			conn = DBConnector.connect();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int result = ps.executeUpdate();
			System.out.println(result + "thông tin được xóa");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//cap nhat thong tin cu dan
	public static void updateCuDan(int macd,String hoten, String email, String sodt, String quequan) {
		String sql = "update cudan\r\n"
				+ "set hoten = ?,\r\n"
				+ "email = ?,\r\n"
				+ "sdt = ?,\r\n"
				+ "quequan = ?,\r\n"
				+ "where id =?";
		try {
			conn = DBConnector.connect();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,hoten);
			ps.setString(2, email);
			ps.setString(3, sodt);
			ps.setString(4, quequan);
			ps.setInt(5, macd);
			int result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
