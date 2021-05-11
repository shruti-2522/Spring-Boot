package com.practice.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(Model m) {

		System.out.println("Inside About Handler");
		m.addAttribute("name", "Shruti Amrutkar");
		m.addAttribute("currentDate", new Date());

		return "about";
		// about.html
	}
	
	//handling itterarion
	
	@RequestMapping(value="/example-loop",method=RequestMethod.GET)
	public String itterate(Model m)
	{
		//Create list,set,,collections
		List<String> list=List.of("Niki","Rushali","Hitesh","Pradhunya","Vishakha");
		
		m.addAttribute("name",list);
		
		return "itterate";
	}
	
	//Handler for conditional statement:
	@GetMapping("/conditional")
	public String conditional(Model m)
	{
		m.addAttribute("isActive",true);
		m.addAttribute("Gender", "M");
		
		List<Integer> list=List.of(10,20,30,40,50,60);
		m.addAttribute("Mylist", list);
		return "condition";
	}
	
	@GetMapping("/service")
	public String service(Model m)
	{
		m.addAttribute("title","I like to do programming..");
		m.addAttribute("subtitle",LocalDateTime.now().toString());
		return "service";
	}
	
	@GetMapping("/aboutnew")
	public String newAbout()
	{
		return "aboutnew";
	}
	
	@GetMapping("/contact")
	public String contact()
	{
		return "contact";
	}

}
