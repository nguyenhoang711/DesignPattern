package com.hoangnguyen.QuanLyDanCu.entity;
//

/**
 * This class is . 
 * 
 * @Description: .
 * @author: NguyenHoang
 * @create_date: Dec 7, 2022
 * @version: 1.0
 * @modifer: NguyenHoang
 * @modifer_date: Dec 7, 2022
 */
public class QuanLy {
	private int maAdmin;
	private TaiKhoan adminAccount;
	
	public int getMaAdmin() {
		return maAdmin;
	}
	public void setMaAdmin(int maAdmin) {
		this.maAdmin = maAdmin;
	}
	public TaiKhoan getAdminAccount() {
		return adminAccount;
	}
	public void setAdminAccount(TaiKhoan adminAccount) {
		this.adminAccount = adminAccount;
	}
}
