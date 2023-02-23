//
package com.hoangnguyen.QuanLyDanCu.entity;

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
public class NhanVien extends Person{
	private int maNhanVien;
	private TaiKhoan taikhoan;
	
	public int getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(int maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public TaiKhoan getTaikhoan() {
		return taikhoan;
	}
	
	public void setTaikhoan(TaiKhoan taikhoan) {
		this.taikhoan = taikhoan;
	}
}
