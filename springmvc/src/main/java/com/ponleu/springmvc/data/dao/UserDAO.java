package com.ponleu.springmvc.data.dao;

import com.ponleu.springmvc.model.UserDTO;


public interface UserDAO {
	UserDTO getByUsername(String username);
}
