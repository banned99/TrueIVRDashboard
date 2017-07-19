package com.truecorp.dashboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.truecorp.dashboard.model.Authorize;
import com.truecorp.dashboard.util.DBUtil;

public class LoginDao {

	public static Authorize userLogin(String username, String password) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM userlan ul left outer join role r on ul.role_id = r.role_id WHERE userlan=? and pwd=? LIMIT 1";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
			Authorize auth = null;

			while (rs.next()) {
				auth = new Authorize();
				auth.setUsername(rs.getString("userlan"));
				auth.setUserFullname(rs.getString("userlan_name"));
				auth.setUserRole(rs.getString("role_name"));
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