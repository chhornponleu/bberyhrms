package com.ponleu.springmvc.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="auth")
public class AuthController {
	private static final String LOGIN_PAGE = "auth/login";
	
	@RequestMapping(method=RequestMethod.GET)
	public String index() {
		return login();
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login() {
		return LOGIN_PAGE;
	}
}
