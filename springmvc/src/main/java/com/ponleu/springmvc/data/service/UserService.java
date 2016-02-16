package com.ponleu.springmvc.data.service;

import com.ponleu.springmvc.model.UserDTO;

public interface UserService {
	UserDTO getByUsername(String username);
}
