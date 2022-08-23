package com.cts.pensionmanagementsystem.pensionerdetail.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name="authorization",url = "localhost:8010")
public interface AuthorizationClient {
		
	@GetMapping("/Authorize")
	public Boolean authorization(@RequestHeader("Authorization") String token);
	
}
