package com.cts.pensionmanagementsystem.pensionerdetail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.pensionmanagementsystem.pensionerdetail.client.AuthorizationClient;
import com.cts.pensionmanagementsystem.pensionerdetail.entity.PensionerDetail;
import com.cts.pensionmanagementsystem.pensionerdetail.service.PensionerDetailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
public class PensionerDetailController {

	@Autowired
	PensionerDetailService pensionerDetailService;

	@Autowired
	PensionerDetail pensionerDetail;

	@Autowired
	AuthorizationClient authorizationClient;

	@GetMapping(value = "/PensionerDetailByAadhaar/{aadhaarNumber}")
	public PensionerDetail pensionerDetailByAadhaarNumber(@RequestHeader("Authorization") String token,@PathVariable("aadhaarNumber") long aadhaarNumber) {

		log.info("START - pensionerDetailByAadhaarNumber() of PensionerDetailController");
		try {

			if (Boolean.TRUE.equals(authorizationClient.authorization(token))) {

				pensionerDetail = pensionerDetailService.getPensionerDetailsByAadhaar(aadhaarNumber);

				if (pensionerDetail == null) {
					log.error("No Pensioner Detail found for the given Aadhaar Number.");
					throw new NullPointerException(
							"getPensionerDetailsByAadhaar() returned null for the Aadhaar Number " + aadhaarNumber);
				}
			}
		} catch (Exception e) {
			log.error("Exception occurred in pensionerDetailByAadhaarNumber()" + e.getMessage());
		}

		log.info("END - pensionerDetailByAadhaarNumber() of PensionerDetailController");

		return pensionerDetail;

	}
}
