package com.jwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	
	@RequestMapping("/welcome")
	public String home()
	{
		String text="This is the project for";
		text+="to learn JWT Authentication";
		return text;
	}
	
	@RequestMapping("/getuser")
	public String users()
	{
		return "{\"name\":\"Shruti\"}";
	}

}
