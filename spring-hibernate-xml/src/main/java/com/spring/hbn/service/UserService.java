package com.spring.hbn.service;

import java.util.List;

import com.spring.hbn.entity.User;

public interface UserService {
	List<User> list();
	void save(User user);
	void update(User user);
}
