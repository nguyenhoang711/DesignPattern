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
public class CanHo {
	private String id;
	private int soPhong;
	private String toa;
	private int tang;
	private float dientich;
	private Status trangthai;
	private CuDan chuho;
	private double gia;
	private String chitiet;
	public String getId() {
		return id;
	}
	public int getSoPhong() {
		return soPhong;
	}
	public String getToa() {
		return toa;
	}
	public int getTang() {
		return tang;
	}
	
	public float getDientich() {
		return dientich;
	}
	public Status getTrangthai() {
		return trangthai;
	}
	public CuDan getChuho() {
		return chuho;
	}
	public double getGia() {
		return gia;
	}
	public String getChitiet() {
		return chitiet;
	}
	public void setSoPhong(int soPhong) {
		this.soPhong = soPhong;
	}
	public void setToa(String toa) {
		this.toa = toa;
	}
	public void setTang(int tang) {
		this.tang = tang;
	}
	
	public void setDientich(float dientich) {
		this.dientich = dientich;
	}
	public void setTrangthai(Status trangthai) {
		this.trangthai = trangthai;
	}
	public void setChuho(CuDan chuho) {
		this.chuho = chuho;
	}
	public void setGia(double gia) {
		this.gia = gia;
	}
	public void setChitiet(String chitiet) {
		this.chitiet = chitiet;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "CanHo [id=" + id + ", soPhong=" + soPhong + ", toa=" + toa + ", tang=" + tang + ", dientich=" + dientich
				+ ", trangthai=" + trangthai.name() + ", gia=" + gia + ", chitiet=" + chitiet + "]";
	}
	
	
}
