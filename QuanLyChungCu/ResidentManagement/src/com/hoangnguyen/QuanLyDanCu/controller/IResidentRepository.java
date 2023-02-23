//
package com.hoangnguyen.QuanLyDanCu.controller;

import java.sql.SQLException;
import java.util.List;

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
public interface IResidentRepository {
	public int Login(String soDienThoai, String password);
	public String[][] printAllResident();
	public String[][] printAllApartment();
	public String[][] printAllContract();
	public String[][] printAllAccount();
}
