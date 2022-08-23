package com.cts.pensionmanagementsystem.authorization.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.pensionmanagementsystem.authorization.entity.LoginRequest;
import com.cts.pensionmanagementsystem.authorization.entity.LoginResponse;
import com.cts.pensionmanagementsystem.authorization.exception.ResourceNotFound;
import com.cts.pensionmanagementsystem.authorization.service.JwtUtilService;
import com.cts.pensionmanagementsystem.authorization.service.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class AuthorizationController {

	private final JwtUtilService jwtUtilService;
	private final UserDetailsServiceImpl userDetailService;
	private final AuthenticationManager authenticationManager;

	@PostMapping("/Authenticate")
	public ResponseEntity<LoginResponse> generateToken(@RequestBody LoginRequest loginRequest) throws ResourceNotFound {
		log.info("START - generateToken() of AuthorizationController");

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		} catch (Exception e) {
			log.error("EXCEPTION - generateToken() of AuthorizationController");
			throw new ResourceNotFound("User Not Found");
		}

		log.info("END - generateToken() of AuthorizationController");

		return ResponseEntity.ok(new LoginResponse(jwtUtilService.generateToken(loginRequest.getUsername())));
	}

	// validation of the generated jwt token to access '/authorize' endpoint

	@GetMapping("/Authorize")
	public ResponseEntity<Boolean> authorization(@RequestHeader("Authorization") String token) {

		log.info("START - authorization() of AuthorizationController");

		token = token.substring(7);
		var user = userDetailService.loadUserByUsername(jwtUtilService.extractUsername(token));

		if (Boolean.TRUE.equals(jwtUtilService.validateToken(token, user))) {
			log.info("END - authorization() of AuthorizationController");
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			log.info("END - authorization() of AuthorizationController");
			return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
		}

	}

}
