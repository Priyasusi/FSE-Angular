package com.cts.pensionmanagementsystem.authorization.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.pensionmanagementsystem.authorization.exception.ResourceNotFound;
import com.cts.pensionmanagementsystem.authorization.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	// loading user name from user database passing to spring provided UserDetails

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			log.info("START - loadUserByUsername() UserDetailsServiceImpl");
			var userInfo = userRepository.findByUsername(username);
			log.info("END - loadUserByUsername() UserDetailsServiceImpl");
			return new User(userInfo.getUsername(), userInfo.getPassword(), new ArrayList<>());

		} catch (Exception e) {
			log.error("EXCEPTION - loadUserByUsername() UserDetailsServiceImpl");
			throw new ResourceNotFound("User with the given username not found");
		}

	}

}
