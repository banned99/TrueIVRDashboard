package com.truecorp.dashboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.truecorp.dashboard.util.DBUtil;
import com.truecorp.dashboard.exception.InvalidLoginException;
import com.truecorp.dashboard.model.Authorize;

public class LoginDao {

	public static Authorize userLogin(String username, String password) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Authorize WHERE username=? and password=? LIMIT 1";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
			Authorize auth = null;

			if (rs != null) {
				while (rs.next()) {
					auth = new Authorize();
					auth.setUserID(rs.getString("user_id"));
					auth.setUsername(rs.getString("username"));
					auth.setUserFullname(rs.getString("user_fullname"));
					auth.setUserRole(rs.getString("user_role"));
//					auth.setbalh(rs.getString("blah"));
					// ...
				}
			} else {
				throw new InvalidLoginException("Username or password is incorrect;");
			}

			return auth;
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return null;
	}

	private static Connection getConnection() throws SQLException {
		Connection conn = DBUtil.getConnection();
		return conn;
	}

}