package com.truecorp.dashboard.service;

import java.sql.SQLException;

import com.truecorp.dashboard.dao.LoginDao;
import com.truecorp.dashboard.model.Authorize;

public class LoginService {

	public Authorize doLogin(String username, String password) throws SQLException {
		return LoginDao.userLogin(username, password);
	}
}