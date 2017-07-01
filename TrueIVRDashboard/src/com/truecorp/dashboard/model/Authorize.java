package com.truecorp.dashboard.model;

public class Authorize {
	private String userID;
	private String username;
	private String userFullname;
	private String userRole;

	public String getUserID() {
		return this.userID;
	}

	public String getUsername() {
		return this.username;
	}

	public String getUserRole() {
		return this.userRole;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserFullname() {
		return userFullname;
	}

	public void setUserFullname(String userFullname) {
		this.userFullname = userFullname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserRole(String role) {
		this.userRole = role;
	}
	
}