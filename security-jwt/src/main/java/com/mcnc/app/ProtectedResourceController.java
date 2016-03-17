package com.mcnc.app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/rest")
public class ProtectedResourceController {
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public String p() {
		return "This is protected";
	} 
	
	@ResponseBody
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String pp() {
		return "This is protected";
	}
}
