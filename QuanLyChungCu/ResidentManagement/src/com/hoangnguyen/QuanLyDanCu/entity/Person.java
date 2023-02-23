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
public class Person {
	private String hoTen;
	private String Email;
	private String soDienThoai;
	private String queQuan;
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public String getQueQuan() {
		return queQuan;
	}
	public void setQueQuan(String queQuan) {
		this.queQuan = queQuan;
	}
	
	@Override
	public String toString() {
		return "Person [hoTen=" + hoTen + ", Email=" + Email + ", soDienThoai=" + soDienThoai
				+ ", queQuan=" + queQuan + "]";
	}
	
}
