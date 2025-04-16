package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {
	public static boolean validate(String email, String password) {
		boolean status = false;
		try {
			Connection con = ConProviderDao.getConnection();
			PreparedStatement ps = con
					.prepareStatement("select * from frndschat_user where email=? and pass=? and authorized=?");
			ps.setString(1, email);
			ps.setString(2, password);
			ps.setString(3, "yes");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				status = true;
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}
}
