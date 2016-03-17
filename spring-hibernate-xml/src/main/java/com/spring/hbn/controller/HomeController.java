package com.spring.hbn.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.hbn.service.UserService;

@Controller
public class HomeController {
	@Autowired
	UserService userService;
	
	@ResponseBody
	@RequestMapping(value="/test")
	public Map<String, Object> test(HttpServletResponse response) throws IOException {
		Map<String, Object> resp = new HashMap<String, Object>();
		Long start=System.currentTimeMillis();
		resp.put("user_list", userService.list());
		System.out.println(System.currentTimeMillis()-start);
		return resp; 
	}
}
