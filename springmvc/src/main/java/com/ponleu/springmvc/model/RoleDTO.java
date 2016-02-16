package com.ponleu.springmvc.model;

public class RoleDTO {
	private int roleId;
	private String roleName;
	private String roleDescription;
	private String roleActive;
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public String getRoleActive() {
		return roleActive;
	}
	public void setRoleActive(String roleActive) {
		this.roleActive = roleActive;
	}
	
}
