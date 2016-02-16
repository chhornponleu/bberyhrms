package com.ponleu.springmvc.apptest.service;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ponleu.springmvc.apptest.TestBase;
import com.ponleu.springmvc.data.service.RoleService;

public class RoleServiceTest extends TestBase {
	
	@Autowired
	RoleService roleService;
	
	@Test
	public void test() throws JsonGenerationException, JsonMappingException, IOException { 
		System.out.println(this.getStringFromObject(roleService.getRoleList()));
	}
	
	@Test
	public void test1() throws JsonGenerationException, JsonMappingException, IOException { 
		System.out.println(this.getStringFromObject(roleService.getRoleListByUserId(1)));
	}
	
	
}
