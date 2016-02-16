package com.ponleu.springmvc.apptest.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ponleu.springmvc.apptest.TestBase;
import com.ponleu.springmvc.data.service.UserService;

public class UserServiceTest extends TestBase {
	
	@Autowired
	UserService userService;
	
	@Test
	public void test()  { 
		System.out.println(this.getStringFromObject(userService.getByUsername("ponleu")));
	}
}
