package com.validation.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.validation.entities.LoginData;

@Controller
public class MainController {
	@GetMapping("/form")
	public String openForm(Model m)
	{
		System.out.println("Open form");
		m.addAttribute("loginData",new LoginData());
		return "form";
	}
	
	@PostMapping("/process")
	public String proccessForm(@Valid @ModelAttribute("loginData") LoginData loginData, BindingResult b1)
	{
		if(b1.hasErrors())
		{
			System.out.println(b1);
			return "form";
		}
		System.out.println(loginData);
		return "success";
	}

}
