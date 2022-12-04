package com.dm.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCutil {
	final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	final static String JDBC_URL = "jdbc:mysql://221.141.68.153/comproject?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=utf-8";
	final static String JDBC_USER = "newuser"; //계정
	final static String JDBC_PASS = "1234"; //
	
	public static Connection getConnection() { 
		try {
			Class.forName(JDBC_DRIVER);
			return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS); 
				
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void close(PreparedStatement pstmt, Connection conn) {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			pstmt.close();
			conn.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

