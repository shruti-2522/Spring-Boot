package com.Restapi.book.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHandler {
	
	//public final String UPLOAD_DIR="C:\\Users\\Shruti\\Documents\\workspace-spring-tool-suite-4-4.10.0.RELEASE\\spingRestapi\\src\\main\\resources\\static\\images";
     public final String UPLOAD_DIR=new ClassPathResource("static/images/").getFile().getAbsolutePath();
     
     public FileUploadHandler()throws IOException
     {
    	 
     }
	public boolean uploadFile(MultipartFile f) {
		boolean f1=false;
		
		try
		{
			
			//
			/*
			 * InputStream is=f.getInputStream(); byte data[]=new byte[is.available()];
			 * is.read(data);
			 * 
			 * //write FileOutputStream fs=new
			 * FileOutputStream(UPLOAD_DIR+File.separator+f.getOriginalFilename());
			 * fs.write(data); fs.flush(); fs.close();
			 */
			
			Files.copy(f.getInputStream(),Paths.get(UPLOAD_DIR+File.separator+f.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
			f1=true;
		}
		
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return f1;
	}
}
