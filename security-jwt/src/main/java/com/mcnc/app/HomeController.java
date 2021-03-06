package com.mcnc.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public String home() {
		return "This is protected";
	}
	
	@ResponseBody
	@RequestMapping(value="/protected", method=RequestMethod.GET)
	public String p() {
		return "This is protected";
	}
}
