package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.dao.UserRepository;
import com.smart.entities.User;

@Controller
public class SampleController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/test")
	@ResponseBody
	public String test()
	{
		User u1=new User();
		u1.setName("Shruti Amrutkar");
		u1.setEmail("shrutiamrutkar@gmail.com");
		userRepository.save(u1);
		
		
		return "Working";
		
		
	}

}
