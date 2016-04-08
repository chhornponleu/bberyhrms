package com.spring.hbn.test.service;


import java.util.HashSet;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.hbn.dao.UserDAO;
import com.spring.hbn.entity.Role;
import com.spring.hbn.entity.User;
import com.spring.hbn.service.RoleService;
import com.spring.hbn.service.UserService;
import com.spring.hbn.test.TestBase;

public class TestUserService extends TestBase{
	
	@Autowired
	UserService uSvr;
	
	@Autowired
	@Qualifier(value="userDAO")
	UserDAO usrDAO;
	
	@Autowired
	RoleService rSvr;
	
	@Test
	public void save() {
		User user = new User();
		user.setUsername("usr"+System.currentTimeMillis());
		user.setEmail("user"+System.currentTimeMillis()+"@wingmoney.com");
		user.setPassword("123");
		user.setEnabled(true);
		
		Role role = new Role();
		role.setRoleName("ADMIN");
		role.setEnabled(true);
		role.setDescription("test admin");
		
		if(user.getRoles()== null) {
			user.setRoles(new HashSet<Role>());
		}
		user.getRoles().add(role);
		
		uSvr.save(user);
	}
	
	@Test
	public void savelist() {
		int i=10000;
		while(i>0) {
			i--;
			
			User user = new User();
			user.setUsername("usr"+System.currentTimeMillis());
			user.setEmail("user"+System.currentTimeMillis()+"@wingmoney.com");
			user.setPassword("123");
			user.setEnabled(true);
			user.getRoles().add(rSvr.getById(2));
			
			uSvr.save(user);
		}
	}
	
	@Test
	public void getById() throws JsonProcessingException {
		System.out.println(new ObjectMapper().writeValueAsString(uSvr.getByUserId(1)));
	}
	
	@Test
	public void update() {
		
	}
	
	@Test
	public void list() throws JsonProcessingException {
		Long start = System.currentTimeMillis();
		System.out.println(new ObjectMapper().writeValueAsString(usrDAO.findAll()));
		System.out.println(System.currentTimeMillis() - start);
	}
}
