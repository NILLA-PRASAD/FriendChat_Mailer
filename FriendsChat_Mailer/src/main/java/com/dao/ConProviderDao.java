package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConProviderDao {
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/romio", "root", "root");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
}
