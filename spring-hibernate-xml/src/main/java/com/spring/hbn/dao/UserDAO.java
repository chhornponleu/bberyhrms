package com.spring.hbn.dao;

import com.spring.hbn.entity.User;

public interface UserDAO extends BaseDAO<User> {
	User getByUserId(Integer userId);
}
