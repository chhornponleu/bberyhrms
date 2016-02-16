package com.ponleu.springmvc.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ponleu.springmvc.data.dao.RoleDAO;
import com.ponleu.springmvc.data.service.RoleService;
import com.ponleu.springmvc.model.RoleDTO;

@Transactional
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleDAO roleDAO;
	
	public List<RoleDTO> getRoleList() {
		return roleDAO.getRoleList();
	}

	public List<RoleDTO> getRoleListByUserId(int userId) {
		return roleDAO.getRoleListByUserId(userId);
	}

}
