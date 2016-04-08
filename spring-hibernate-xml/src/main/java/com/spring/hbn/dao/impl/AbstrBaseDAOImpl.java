package com.spring.hbn.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.hbn.dao.BaseDAO;

public abstract class AbstrBaseDAOImpl<T> implements BaseDAO<T>{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public abstract Class<T> getClassGenericType();
	
	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Criteria cri = getSession().createCriteria(getClass());
		return cri.list();
	}

	public void save(T entity) {
		getSession().save(entity);
	}

	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public void delete(Integer id) {
		getSession().delete(id);
	}

}
