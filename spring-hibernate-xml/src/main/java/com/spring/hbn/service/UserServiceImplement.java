package com.spring.hbn.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.hbn.entity.User;

@Service
public class UserServiceImplement implements UserService {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public List<User> list() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>)  session.createCriteria(User.class)
				//.add(Restrictions.gt("id", 20000))
				.add(Restrictions.like("username", "%u%")) 
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		//using store procedure
		/*Session session = sessionFactory.getCurrentSession();
		Query qr = session.getNamedQuery("sp_getUserTest");
		qr.setParameter("search", "%u%");
		@SuppressWarnings("unchecked")
		List<User> list = qr.list();*/
		return list;
	}
	
	@Transactional
	public void insert(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

}
