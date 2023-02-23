package com.hoangnguyen.QuanLyDanCu.entity;
import java.time.LocalDate;
import java.util.List;

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
public class HopDong {
	private int id;
	private CuDan benA;
	private QuanLy benB;
	private CanHo canho;
	private Type loaiHD;
	private int kiHan;
	private LocalDate ngayKy;
	private String mota;
	public CanHo getCanho() {
		return canho;
	}
	public void setCanho(CanHo canho) {
		this.canho = canho;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public CuDan getBenA() {
		return benA;
	}
	public void setBenA(CuDan benA) {
		this.benA = benA;
	}
	public QuanLy getBenB() {
		return benB;
	}
	public void setBenB(QuanLy benB) {
		this.benB = benB;
	}
	
	public Type getLoaiHD() {
		return loaiHD;
	}
	public void setLoaiHD(Type loaiHD) {
		this.loaiHD = loaiHD;
	}
	public int getKiHan() {
		return kiHan;
	}
	public void setKiHan(int kiHan) {
		this.kiHan = kiHan;
	}
	public LocalDate getNgayKy() {
		return ngayKy;
	}
	public void setNgayKy(LocalDate ngayKy) {
		this.ngayKy = ngayKy;
	}
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	public int getId() {
		return id;
	}
	
	
}
