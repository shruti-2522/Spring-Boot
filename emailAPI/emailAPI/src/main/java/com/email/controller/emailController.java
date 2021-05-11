package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.email.Model.EmailRequest;
import com.email.service.emailService;

@RestController
public class emailController {
	@Autowired
	private emailService emailService;
	
	@RequestMapping("/welcome")
	public String Welcome()
	{
		return "Hello World";
	}
	
	//API to send email
	@RequestMapping(value="/sendmail",method=RequestMethod.POST)
	public ResponseEntity<?> sendemail(@RequestBody EmailRequest emailRequest)
	{
		
		System.out.println(emailRequest);
	    boolean result=this.emailService.sendMail(emailRequest.getSubject(),emailRequest.getMessage(),emailRequest.getTo());
		if(result)
		{
			return ResponseEntity.ok("Email is sent successfully..");
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("email not sent");
		}
	    
	    
		
	}

}
