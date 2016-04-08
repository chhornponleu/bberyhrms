package com.spring.hbn.service;

import com.spring.hbn.entity.Role;

public interface RoleService {
	Role getById(Integer roleId);
	void saveOrUpdate(Role role);
}
