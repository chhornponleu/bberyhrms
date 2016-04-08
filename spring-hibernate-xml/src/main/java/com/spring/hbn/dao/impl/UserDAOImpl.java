package com.spring.hbn.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.spring.hbn.dao.UserDAO;
import com.spring.hbn.entity.User;

@Repository(value="userDAO")
public class UserDAOImpl extends AbstrBaseDAOImpl<User> implements UserDAO {

	@Override
	public Class<User> getClassGenericType() {
		return User.class;
	}

	public User getByUserId(Integer userId) {
		Session session = getSession();
		Query query = session.getNamedQuery("getByUserId");
		query.setParameter("userId", userId);
		return (User) query.uniqueResult();
	}
}
