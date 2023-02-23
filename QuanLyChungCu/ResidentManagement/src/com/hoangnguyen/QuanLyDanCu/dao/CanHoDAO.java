//
package com.hoangnguyen.QuanLyDanCu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
public class CanHoDAO {
	private static Connection conn = null;
	
	//lay ra tat ca can ho
	public static ArrayList<String[]> getAllCanHo(){
		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String[] arrayString;
		int columnSize;//kich thuoc cua 1 ban ghi
		//lay thong tin trong bang tablename
		try {
			String sql = "select *\r\n"
					+ "from canho";
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
	
	//lay ra tat ca can ho ma chua duoc thue
	public static Object[] getAllMaCanHo() {
		ArrayList<Object> macanho = new ArrayList<Object>();
		String sql = "SELECT id from canho\r\n"
				+ "where trangthai ='AVAILABLE'";
		try {
			conn = DBConnector.connect();
			Statement stm = conn.createStatement();
			ResultSet res = stm.executeQuery(sql);
			while(res.next()) {
				macanho.add(res.getString(1));
			}
			return macanho.toArray();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return macanho.toArray();
	}
	
	public static boolean addNewCanHo(String toa, int tang, int sophong, int dientich, float gia, String trangthai, String mota) {
		String sql = "INSERT INTO canho(id,sophong,toa,tang,dientich,gia,trangthai,chitiet)VALUES (?,?,?,?,?,?,?,?)";
		int result;
		try {
			conn = DBConnector.connect();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, toa + "-"+ sophong);
			ps.setInt(2, sophong);
			ps.setString(3, toa);
			ps.setInt(4,tang);
			ps.setInt(5, dientich);
			ps.setFloat(6,gia);
			ps.setString(7, trangthai);
			ps.setString(8, mota);
			result = ps.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	//cap nhat thong tin can ho
	public static void updateCanHo(String mach, float gia,String loai) {
		String sql = "update canho\r\n"
				+ "gia = ?,\r\n"
				+ "trangthai = ?,\r\n"
				+ "where id =?";
		try {
			conn = DBConnector.connect();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setFloat(1, gia);
			ps.setString(2, loai);
			ps.setString(3, mach);
			int result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//xóa thông tin căn hộ
	public static void deleteCanHo(String id) {
		String sql = "DELETE FROM canho WHERE id = ?";
		try {
			conn = DBConnector.connect();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			int result = ps.executeUpdate();
			System.out.println(result + "thông tin được xóa");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
