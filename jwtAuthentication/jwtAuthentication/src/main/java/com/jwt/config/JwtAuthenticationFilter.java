package com.jwt.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jwt.helper.jwtUtil;
import com.jwt.security.customUserDetailsService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	
	
	@Autowired
	private customUserDetailsService customUserDetailsService;
	
	@Autowired
	private jwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
	
		//get JWT
		//Bearer
		//validate
		
		String requesttokenHeader = request.getHeader("Authorization");
		String username=null;
		String jwtToken=null;
		
		
		//Check null and format
		if(requesttokenHeader!=null && requesttokenHeader.startsWith("Bearer "))
		{
			jwtToken=requesttokenHeader.substring(7);
			
			try
			{
				
			 username=this.jwtUtil.getUsernameFromToken(jwtToken);
			}
			catch (Exception e) {
				
				e.printStackTrace();
			}
			
			UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
			
			//security
			
			if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null)
			{
			  UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,userDetails.getAuthorities());
			  usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			  SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			else
			{
				System.out.println("Token is not validated");
			}
			
		}
		
		filterChain.doFilter(request, response);
	}
	

}
