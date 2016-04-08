package com.spring.hbn.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.hbn.dao.BaseDAO;
import com.spring.hbn.dao.UserDAO;
import com.spring.hbn.entity.User;

@Service
public class UserServiceImplement extends AbstrBaseService<User> implements UserService {
	@Autowired
	UserDAO userDAO;
	
	@Override
	public BaseDAO<User> getBaseDAO() {
		return userDAO;
	}
	
//	@SuppressWarnings("unchecked")
//	@Transactional
//	public List<User> list() {
//		Session session = getSession();
//		Criteria cri = session.createCriteria(User.class);
//		cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
//		return (List<User>) cri.list();
//	}
//
//	@Transactional
//	public void save(User user) {
//		getSession().save(user);
//	}
//
//	@Transactional
//	public void update(User user) {
//		getSession().update(user);
//	}
	
	@Transactional
	public User getByUserId(Integer userId) {
		return userDAO.getByUserId(userId);
	}
	
}

// .add(Restrictions.gt("id", 20000))
// .add(Restrictions.like("username",
// "%u%")).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

// using store procedure
/*
 * Session session = sessionFactory.getCurrentSession(); Query qr =
 * session.getNamedQuery("sp_getUserTest"); qr.setParameter("search", "%u%");
 * 
 * @SuppressWarnings("unchecked") List<User> list = qr.list();
 */
