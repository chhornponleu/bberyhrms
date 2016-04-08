package com.spring.hbn.service;

import com.spring.hbn.dao.UserDAO;
import com.spring.hbn.entity.User;

public interface UserService extends UserDAO {
	User getByUserId(Integer userId);
}
