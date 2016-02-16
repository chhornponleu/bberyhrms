package com.ponleu.springmvc.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ponleu.springmvc.data.dao.UserDAO;
import com.ponleu.springmvc.data.service.UserService;
import com.ponleu.springmvc.model.UserDTO;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDAO;

	public UserDTO getByUsername(String username) {
		return userDAO.getByUsername(username);
	}
	
}
