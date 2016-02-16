package com.ponleu.springmvc.data.service;

import java.util.List;

import com.ponleu.springmvc.model.RoleDTO;

public interface RoleService {
	List<RoleDTO> getRoleList();
	List<RoleDTO> getRoleListByUserId(int userId);
}
