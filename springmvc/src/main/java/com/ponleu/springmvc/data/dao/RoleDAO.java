package com.ponleu.springmvc.data.dao;

import java.util.List;

import com.ponleu.springmvc.model.RoleDTO;

public interface RoleDAO {
	List<RoleDTO> getRoleList();
	List<RoleDTO> getRoleListByUserId(int userId);
}
