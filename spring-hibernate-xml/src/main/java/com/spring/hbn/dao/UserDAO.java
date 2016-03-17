package com.spring.hbn.dao;

import java.util.List;

import com.spring.hbn.entity.User;

public interface UserDAO {
	public List<User> list();
	public void insert(User user);
}
