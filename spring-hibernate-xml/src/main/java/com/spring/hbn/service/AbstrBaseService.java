package com.spring.hbn.service;

import java.util.List;

import com.spring.hbn.dao.BaseDAO;

public abstract class AbstrBaseService<T> implements BaseDAO<T> {

	public abstract BaseDAO<T> getBaseDAO();

	public List<T> findAll() {
		return getBaseDAO().findAll();
	}

	public void save(T entity) {
		getBaseDAO().save(entity);
	}

	public void saveOrUpdate(T entity) {
		getBaseDAO().saveOrUpdate(entity);
	}

	public void update(T entity) {
		getBaseDAO().update(entity);
	}

	public void delete(Integer id) {
		getBaseDAO().delete(id);
	}

}
