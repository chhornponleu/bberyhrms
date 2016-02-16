package com.ponleu.springmvc.model;

import java.util.List;

public class UserDTO {

	private int userId;
	private String username;
	private String password;
	private boolean userActive;
	private List<RoleDTO> roleList;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public boolean isUserActive() {
		return userActive;
	}
	public void setUserActive(boolean userActive) {
		this.userActive = userActive;
	}
	public List<RoleDTO> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<RoleDTO> roleList) {
		this.roleList = roleList;
	}
	
	
}
