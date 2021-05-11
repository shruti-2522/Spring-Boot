package com.example.devtool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@RequestMapping("/test")
	@ResponseBody
	public String test()
	{
		int a=9;
		int b=9;
		int c=10;
		int d=10;
		return "Sum Of a and b is="+(a+b+c+d);
		

	}

}
