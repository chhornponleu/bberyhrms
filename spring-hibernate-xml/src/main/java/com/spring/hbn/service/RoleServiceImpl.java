package com.spring.hbn.service;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.hbn.entity.Role;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	SessionFactory sf;

	@Transactional
	public Role getById(Integer roleId) {
		Criteria cri = sf.getCurrentSession().createCriteria(Role.class);
		cri.add(Restrictions.idEq(roleId));
		return (Role) cri.uniqueResult();
	}

	@Transactional
	public void saveOrUpdate(Role role) {
		sf.getCurrentSession().saveOrUpdate(role); 
	}
}
