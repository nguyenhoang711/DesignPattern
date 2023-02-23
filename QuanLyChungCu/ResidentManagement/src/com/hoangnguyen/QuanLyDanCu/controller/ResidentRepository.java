//
package com.hoangnguyen.QuanLyDanCu.controller;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.hoangnguyen.QuanLyDanCu.utils.*;
import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;
import com.hoangnguyen.QuanLyDanCu.dao.AccountDAO;
import com.hoangnguyen.QuanLyDanCu.dao.CanHoDAO;
import com.hoangnguyen.QuanLyDanCu.dao.CuDanDAO;
import com.hoangnguyen.QuanLyDanCu.dao.HopDongDAO;
import com.hoangnguyen.QuanLyDanCu.entity.*;


/**
 * This class is . 
 * 
 * @Description: .
 * @author: NguyenHoang
 * @create_date: Dec 9, 2022
 * @version: 1.0
 * @modifer: NguyenHoang
 * @modifer_date: Dec 9, 2022
 */
public class ResidentRepository implements IResidentRepository{
	private static Connection conn = null;
	
	//chuyen ten cu dan ve ma cu dan
	public static int nameToMaCD(String hoten) {
		int id = 0;
		String sql = "select id from cudan\r\n"
				+ "where hoten = ?";
		try {
			conn = DBConnector.connect();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, hoten);
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
	
	//chuyen username ve role cua account
	public static String roleConverter(String username) {
		String role = null;
		String sql = "SELECT `role` FROM taikhoan\r\n"
				+ "where username= ?";
		try {
			conn = DBConnector.connect();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				role = res.getString(1);
			}
			return role;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return role;
	}

	
	//chuyen ve mang chuoi
	private String[][] listConvertToArray(ArrayList<String[]> temp){
		String[][] data = new String[][] {};
		if(temp.size() != 0) {
			data = new String[temp.size()][temp.get(0).length];
			for(int i = 0; i < data.length; i++) {
				for(int j = 0; j < data[i].length; j++) {
					data[i][j] = temp.get(i)[j];
				}
			}
		}
		
		return data;
		
	}
	/* 
	* @see com.hoangnguyen.QuanLyDanCu.controller.IResidentRepository#Login(java.lang.String, java.lang.String)
	*/
	public int Login(String soDienThoai, String password) {
		try {
			String query = "SELECT * from taikhoan WHERE username= ? AND pass = ?";
			conn = DBConnector.connect();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, soDienThoai);
			ps.setString(2, password);
			ResultSet res = ps.executeQuery();
			if(res.next()) return 1;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return -1;
	}
	/* 
	* @see com.hoangnguyen.QuanLyDanCu.controller.IResidentRepository#printAllResident()
	*/
	
	public String[][] printAllResident() {
		ArrayList<String[]> temp = CuDanDAO.getAllCuDan();
		return listConvertToArray(temp);
	}
	
	/* 
	* @see com.hoangnguyen.QuanLyDanCu.controller.IResidentRepository#printAllContract()
	*/
	public String[][] printAllContract() {
		ArrayList<String[]> temp = HopDongDAO.getAllHopDong();
		return listConvertToArray(temp);
	}
	
	/* 
	* @see com.hoangnguyen.QuanLyDanCu.controller.IResidentRepository#printAllApartment()
	*/
	public String[][] printAllApartment() {
		ArrayList<String[]> temp = CanHoDAO.getAllCanHo();
		return listConvertToArray(temp);
	}
	
	
	
	/* 
	* @see com.hoangnguyen.QuanLyDanCu.controller.IResidentRepository#printAllAccount()
	*/
	public String[][] printAllAccount() {
		ArrayList<String[]> temp = AccountDAO.getAllAccount();
		return listConvertToArray(temp);
	}



	
	
	
	
}
