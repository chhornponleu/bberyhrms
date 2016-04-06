package com.spring.hbn.test.service;


import java.util.HashSet;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.hbn.entity.Role;
import com.spring.hbn.entity.User;
import com.spring.hbn.service.UserService;
import com.spring.hbn.test.TestBase;

public class TestUserService extends TestBase{
	
	@Autowired
	UserService uSvr;
	
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
	public void update() {
		
	}
	
	@Test
	public void list() throws JsonProcessingException {
		Long start = System.currentTimeMillis();
		System.out.println(new ObjectMapper().writeValueAsString(uSvr.list()));
		System.out.println(System.currentTimeMillis() - start);
	}
}
