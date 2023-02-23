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
 * @create_date: Dec 30, 2022
 * @version: 1.0
 * @modifer: NguyenHoang
 * @modifer_date: Dec 30, 2022
 */
public class HopDongDAO {

	private static Connection conn = null;
	
	//lay ra danh sach cac hop dong duoc tao
	public static ArrayList<String[]> getAllHopDong(){
		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String[] arrayString;
		int columnSize;//kich thuoc cua 1 ban ghi
		//lay thong tin trong bang tablename
		try {
			String sql = "select *\r\n"
					+ "from hopdong";
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
	
	//am insert dữ liệu vào bảng
	public static void addHopDong(String loai, float kihan, String ngayKy, String mota, int cudanID, int admin_ID, String canho_id) {
		String sql = "INSERT INTO hopdong(loai,kihan,ngayKy,mota,cudan_id,admin_id,canho_id) VALUES\r\n"
				+ "(?,?,?,?,?,?,?)";
		try {
			conn = DBConnector.connect();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, loai);
			ps.setFloat(2, kihan);
			ps.setString(3, ngayKy);
			ps.setString(4, mota);
			ps.setInt(5, cudanID);
			ps.setInt(6, admin_ID);
			ps.setString(7, canho_id);
			int result = ps.executeUpdate();
			System.out.println(result + "hợp đồng được thêm");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//update du lieu trong hop dong
	public static void updateHopDong(int mahd, float kihan,String loai) {
		String sql = "update hopdong\r\n"
				+ "kihan = ?,\r\n"
				+ "trangthai = ?,\r\n"
				+ "where id =?";
		try {
			conn = DBConnector.connect();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setFloat(1, kihan);
			ps.setString(2, loai);
			ps.setInt(3, mahd);
			int result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
