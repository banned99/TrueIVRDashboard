package com.truecorp.dashboard.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

	private static String username = "root";
	private static String password = "banned00";
	private static String hostname = "jdbc:mysql://localhost:3306/trueproject?useSSL=false";

//  temp
	public static void getDBConfig() {
		DBUtil.setUrl(hostname);
		DBUtil.setUser(username);
		DBUtil.setPassword(password);
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			DBUtil.getDBConfig();

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DBUtil.getUrl(),
					DBUtil.getUser(), DBUtil.getPassword());
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static String getUrl() {
		return hostname;
	}

	public static void setUrl(String url) {
		DBUtil.hostname = url;
	}

	public static String getUser() {
		return username;
	}

	public static void setUser(String user) {
		DBUtil.username = user;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		DBUtil.password = password;
	}

}