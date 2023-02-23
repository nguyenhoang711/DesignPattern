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
public class CuDan extends Person{
	
	private int id;
	private Loai loai;
	private String macanho;
	
	public String getMacanho() {
		return macanho;
	}

	public void setMacanho(String macanho) {
		this.macanho = macanho;
	}

	public Loai getLoai() {
		return loai;
	}

	public void setLoai(Loai loai) {
		this.loai = loai;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CuDan [id= " + id + "loai=" + loai + ", mã căn hộ=" + macanho + ", HoTen=" + getHoTen() + ", Email="
				+ getEmail() + ", SoDienThoai=" + getSoDienThoai() + ", QueQuan=" + getQueQuan()
				 + "]";
	}
	
	
}
