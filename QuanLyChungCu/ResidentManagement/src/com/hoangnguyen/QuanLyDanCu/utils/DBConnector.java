//
package com.hoangnguyen.QuanLyDanCu.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	private static Connection conn = null;
	private static boolean isConnected() throws SQLException {
		return conn != null && !conn.isClosed();
	}
	
	public static Connection connect() throws SQLException {
		if(!isConnected()) {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/btlqldancu","nativeuser","Tudo@1-6");
			System.out.println("Ket noi toi DB thanh cong");
		}
		return conn;
	}
	
	public static void disconnect() throws SQLException {
		if(isConnected()) {
			conn.close();
			System.out.println("Ngat ket noi toi DB");
		}
	}
}
