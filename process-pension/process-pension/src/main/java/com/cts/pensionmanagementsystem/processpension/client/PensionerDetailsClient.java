package com.cts.pensionmanagementsystem.processpension.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.pensionmanagementsystem.pensionerdetail.entity.PensionerDetail;

@FeignClient(name = "pensioner-detail", url = "localhost:8020")
public interface PensionerDetailsClient {

	@GetMapping("/PensionerDetailByAadhaar/{aadhaarNumber}")
	public PensionerDetail pensionerDetailByAadhaarNumber(@RequestHeader("Authorization") String token,@PathVariable("aadhaarNumber") long aadhaarNumber);

}
