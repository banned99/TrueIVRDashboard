package com.truecorp.dashboard.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBUtil {

	private static String msg = "Can't Connected Database";
	private static String username = "caliver";
	private static String password = "";
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
//	end temp
	private static Connection init(String s) {
		Connection conn = null;
		try {
			// Connection by JNDI
			InitialContext initContext = new InitialContext();
			Context context = (Context) initContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) context.lookup(s);
			if (dataSource != null) {
				conn = dataSource.getConnection();
				conn.setAutoCommit(false);
				msg = "Connextion: " + conn;
			} else {
				msg = "Error: No DataSource";
			}

		} catch (Exception e) {
			msg = e.getMessage();
		}
		return conn;
	}

	public static Connection getConnectionbyContext() throws SQLException {
		Connection conn = init("jdbc/dashboard");
		if (conn == null) {
			throw new SQLException(msg);
		} else {
			return conn;
		}
	}

}