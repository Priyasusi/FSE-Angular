package com.cts.pensionmanagementsystem.authorization.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwtUtilService {
	
	 private String secretKey = "secret";

	    public String extractUsername(String token) {
	    	log.info("START - extractUsername() of JwtUtilService");
	    	log.info("END - extractUsername() of JwtUtilService");
	        return extractClaim(token, Claims::getSubject);
	    }

	    public Date extractExpiration(String token) {
	    	log.info("START - extractExpiration() of JwtUtilService");
	    	log.info("END - extractExpiration() of JwtUtilService");
	        return extractClaim(token, Claims::getExpiration);
	    }

	    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	    	log.info("START - extractClaim() of JwtUtilService");
	    	log.info("END - extractClaim() of JwtUtilService");
	        final var claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	    }
	    private Claims extractAllClaims(String token) {
	    	log.info("START - extractAllClaims() of JwtUtilService");
	    	log.info("END - extractAllClaims() of JwtUtilService");
	        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	    }

	    private Boolean isTokenExpired(String token) {
	    	log.info("START - isTokenExpired() of JwtUtilService");
	    	log.info("END - isTokenExpired() of JwtUtilService");
	        return extractExpiration(token).before(new Date());
	    }
	    
	    //generating token using the user name

	    public String generateToken(String username) {
	    	log.info("START - generateToken() of JwtUtilService");
	    	log.info("END - generateToken() of JwtUtilService");
	        Map<String, Object> claims = new HashMap<>();
	        return createToken(claims, username);
	    }

	    //create token based on HS256 algorithm using the secret key
	    private String createToken(Map<String, Object> claims, String subject) {
	    	log.info("START - createToken() of JwtUtilService");
	    	log.info("END - createToken() of JwtUtilService");
	        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
	                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
	    }

	    //validating generated jwt token
	    
	    public Boolean validateToken(String token, UserDetails userDetails) {
	    	log.info("START - validateToken() of JwtUtilService");
	    	log.info("END - validateToken() of JwtUtilService");
	        final String username = extractUsername(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }

}
