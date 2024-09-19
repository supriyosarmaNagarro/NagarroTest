package com.spring.retail.pojo;

import java.time.LocalDate;


public class User {
	
	private String userId;
	
	private String username;
	
	private UserCategory userType;
	
	private LocalDate userRegistrationDate;

	public User() {
		super();
	}

	public User(String userId, String username, UserCategory userType, LocalDate userRegistrationDate) {
		super();
		this.userId = userId;
		this.username = username;
		this.userType = userType;
		this.userRegistrationDate = userRegistrationDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserCategory getUserType() {
		return userType;
	}

	public void setUserType(UserCategory userType) {
		this.userType = userType;
	}

	public LocalDate getUserRegistrationDate() {
		return userRegistrationDate;
	}

	public void setUserRegistrationDate(LocalDate userRegistrationDate) {
		this.userRegistrationDate = userRegistrationDate;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", userType=" + userType
				+ ", userRegistrationDate=" + userRegistrationDate + "]";
	}
	
	

}
