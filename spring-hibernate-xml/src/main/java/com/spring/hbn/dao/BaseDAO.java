package com.spring.hbn.dao;

import java.util.List;

public interface BaseDAO<T> {
	
	public List<T> findAll();

	public void save(T entity);

	public void saveOrUpdate(T entity);

	public void update(T entity);

	public void delete(Integer id);
}
