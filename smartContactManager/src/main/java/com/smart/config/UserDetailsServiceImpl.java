package com.smart.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.smart.dao.UserRepository;
import com.smart.entities.User;

public class UserDetailsServiceImpl implements UserDetailsService {
  
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//Fetching user from database:
		 User u1=userRepository.getUserByUserName(username);
		 
		 if(u1==null)
		 {
			 throw new UsernameNotFoundException("Could Not found user");
		 }
		
		 CustomUserDetails c1=new CustomUserDetails(u1);
		 return c1;
		
		
	}

}
