package com.cooksys.ftd.assessment.filesharing.api;

import java.util.HashMap;
import java.util.Map;

public class RegisterUser {
	
	String username;
	String password;
	public RegisterUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RegisterUser(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Map<String, String> registerUser (String username, String password){
		Map<String, String> users = new HashMap<>();
		
		
		return users;
		
		
	}

}
