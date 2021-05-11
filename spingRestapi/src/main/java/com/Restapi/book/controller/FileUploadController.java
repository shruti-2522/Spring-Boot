package com.Restapi.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Restapi.book.helper.FileUploadHandler;

@RestController
public class FileUploadController {
	
	@Autowired
	private FileUploadHandler fileUploadHandler;
	
	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file)
	{
		/*
		 * System.out.println(file.getOriginalFilename());
		 * System.out.println(file.getContentType());
		 * System.out.println(file.getName());
		 * System.out.println(file.getSize());
		 */
		
		//Validation:
		
		try {
		if(file.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must content");
		}
		
		if(!file.getContentType().equals("image/jpeg"))
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("only JPEG content are allowed");
		}
		
		
		//file upload code:
		boolean f=fileUploadHandler.uploadFile(file);
		if(f)
		{
			//return ResponseEntity.ok("File is succesfully uploaded");
			
			return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(file.getOriginalFilename()).toUriString());
		}
		
		}
		catch (Exception e) {
		 e.printStackTrace();
		}
	
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Somethig is wroong!Try again..");
	

}}
