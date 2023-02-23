//
package com.hoangnguyen.QuanLyDanCu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hoangnguyen.QuanLyDanCu.utils.DBConnector;

/**
 * This class is . 
 * 
 * @Description: .
 * @author: NguyenHoang
 * @create_date: Dec 30, 2022
 * @version: 1.0
 * @modifer: NguyenHoang
 * @modifer_date: Dec 30, 2022
 */
public class NhanVienDAO {
	private static Connection conn = null;
	
	// lay ra thong tin cua quan ly theo sodt
	public static String[] getInfor(String sodt) {
		String[] data = {};
		int columnSize;
		String sql = "select hoten,sodt,email\r\n"
				+ "from nhanvien\r\n"
				+ "where sodt = ?";
		try {
			conn = DBConnector.connect();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, sodt);
			ResultSet res = ps.executeQuery();
			columnSize = res.getMetaData().getColumnCount();
			data = new String[columnSize];
			while(res.next()) {
				data[0] =res.getString("hoten");
				data[1] = res.getString("sodt");
				data[2]= res.getString("email");
			}
			return data;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return data;
		
	}
	
	//update thong tin nhan vien
	public static void updateNhanVien(String username,String hoten, String sodt, String email) {
		String sql = "UPDATE nhanvien\r\n"
				+ "SET hoten = ?,\r\n"
				+ "sodt = ?,\r\n"
				+ "email = ?\r\n"
				+ "WHERE sodt = ?";
		try {
			conn = DBConnector.connect();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, hoten);
			ps.setString(2, sodt);
			ps.setString(3, email);
			ps.setString(4, username);
			int result = ps.executeUpdate();
			System.out.println(result + "thanh cong");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
