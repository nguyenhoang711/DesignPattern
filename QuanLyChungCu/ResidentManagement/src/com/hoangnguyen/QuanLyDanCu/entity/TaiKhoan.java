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
public class TaiKhoan {
	private int id;
	private String username;
	private String password;
	private Role role;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "TaiKhoan [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	
}
