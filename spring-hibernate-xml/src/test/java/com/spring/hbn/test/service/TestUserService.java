package com.spring.hbn.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.hbn.service.UserService;
import com.spring.hbn.test.TestBase;

public class TestUserService extends TestBase{
	
	@Autowired
	UserService userservice;
	
	@Test
	public void hello() throws JsonProcessingException {
		Long start = System.currentTimeMillis();
		System.out.println(new ObjectMapper().writeValueAsString(userservice.list()));
		System.out.println(System.currentTimeMillis() - start);
	}
}
