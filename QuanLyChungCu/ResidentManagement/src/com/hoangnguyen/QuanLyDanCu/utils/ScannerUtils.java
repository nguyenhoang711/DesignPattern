//
package com.hoangnguyen.QuanLyDanCu.utils;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class ScannerUtils {
	private static Scanner sc = new Scanner(System.in);
	public static boolean inputTangControl(int tang) {
		return tang >= 1;
	}
	
	public static boolean inputGiaControl(float inputGia) {
		return inputGia > 0;
	}
	
	public static boolean inputPhongControl(int phong) {
		return phong >100;
	}
	
	
	public static String inputString(String inputMess) {
		while(true) {
			try {
				System.out.println(inputMess + ": ");
				String input = sc.nextLine().trim();
				if(input.isBlank()|| input.isEmpty()) throw new Exception("Chuoi khong duoc rong.");
				return input;
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	
	//check gmail hop le
	public static boolean inputEmailControl(String gmail) {
		Pattern p = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
		
		Matcher mat = p.matcher(gmail);
		if(!mat.matches()) return false;
		else return true;
	}
	
	public static String inputPassword(String inputMess) {
		while(true) {
			try {
				System.out.println(inputMess + ": ");
				String password = sc.nextLine().trim();
				Pattern p = Pattern.compile("^[a-z0-9A-Z]{6,12}$");
				Matcher mat = p.matcher(inputMess);
				if(!mat.matches()) throw new Exception("Password khong hop le");
				return password;
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static boolean phoneNumberControl(String phoneNumber) {
		if(phoneNumber.equals("")) {
			return false;
		} else if( (phoneNumber.charAt(0) == '0' && phoneNumber.length() == 10) 
				|| (phoneNumber.charAt(0) != '0' && phoneNumber.length() == 9) 
				|| (phoneNumber.charAt(0) == '+' && phoneNumber.length() == 12)) {
			
			return true;
			
		}
		return false;
		
	}
}
