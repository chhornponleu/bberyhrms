package com.spring.hbn.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.hbn.service.RoleService;
import com.spring.hbn.service.UserService;

@Controller
public class HomeController {
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	SessionFactory sf;
	
	@ResponseBody
	@RequestMapping(value="/test")
	@Transactional
	public Map<String, Object> test(HttpServletResponse response) throws IOException {
		Map<String, Object> resp = new HashMap<String, Object>();
		Long start=System.currentTimeMillis();
		//resp.put("user_list", sf.getCurrentSession().createCriteria(User.class).list());
		resp.put("user_list", userService.findAll());
		System.out.println(System.currentTimeMillis()-start);
		return resp; 
	}
	
	@ResponseBody
	@RequestMapping(value="/role/{roleId}", method=RequestMethod.GET)
	public Map<String, Object> test1(@PathVariable("roleId") Integer roleId) throws IOException {
		Map<String, Object> resp = new HashMap<String, Object>();
		Long start=System.currentTimeMillis();
		resp.put("role", roleService.getById(roleId));
		System.out.println(System.currentTimeMillis()-start);
		return resp; 
	}
	
	@ResponseBody
	@RequestMapping(value="/user/{userId}", method=RequestMethod.GET)
	public Map<String, Object> test11(@PathVariable("userId") Integer userId) throws IOException {
		Map<String, Object> resp = new HashMap<String, Object>();
		Long start=System.currentTimeMillis();
		resp.put("user", userService.getByUserId(userId));
		System.out.println(System.currentTimeMillis()-start);
		return resp; 
	}
}
