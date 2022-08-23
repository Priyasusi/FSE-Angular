package com.cts.pensionmanagementsystem.authorization.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cts.pensionmanagementsystem.authorization.service.JwtUtilService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtilService jwtUtilService;
	@Autowired
	private UserDetailsService userDetailsService;
	
	//filtering jwt token from authorization header starting with string bearer 
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		log.info("START - doFilterInternal() of AuthTokenFilter");
		
		String authrizationHeader=request.getHeader("Authorization");
		
		String token=null;
		String userName=null;
		
		
		if(authrizationHeader !=null && authrizationHeader.startsWith("Bearer"))
		{
			token=authrizationHeader.substring(7);
			userName=jwtUtilService.extractUsername(token);
		}
		
		if(userName !=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			var userDetails=userDetailsService.loadUserByUsername(userName);
			
			if (Boolean.TRUE.equals(jwtUtilService.validateToken(token, userDetails))) {

                var usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
		}
		
		filterChain.doFilter(request, response);
		
		log.info("END - doFilterInternal() of AuthTokenFilter");
	}

}
