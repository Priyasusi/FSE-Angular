package com.cts.pensionmanagementsystem.processpension.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.pensionmanagementsystem.processpension.client.PensionerDetailsClient;
import com.cts.pensionmanagementsystem.processpension.entity.PensionDetail;
import com.cts.pensionmanagementsystem.processpension.entity.ProcessPensionInputRequestModel;
import com.cts.pensionmanagementsystem.processpension.repository.ProcessPensionRepository;
import com.cts.pensionmanagementsystem.processpension.service.ProcessPensionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
public class ProcessPensionController {

	@Autowired
	PensionDetail pensionDetail;

	@Autowired
	PensionerDetailsClient pensionerDetailsClient;

	@Autowired
	ProcessPensionRepository processPensionRepository;

	@Autowired
	ProcessPensionService processPensionService;

	@PostMapping("/ProcessPension")
	public ResponseEntity<PensionDetail> getPensionDetail(@RequestHeader("Authorization") String token,
			@RequestBody ProcessPensionInputRequestModel processPensionInputRequestModel) {

		log.info("START - getPensionDetail() of  ProcessPensionController");
		var pensionerDetail = pensionerDetailsClient.pensionerDetailByAadhaarNumber(token,
				processPensionInputRequestModel.getAadhaarNumber());
		pensionDetail = processPensionService.calculatePensionDetails(pensionerDetail);
		processPensionRepository.save(pensionDetail);

		log.info("END - getPensionDetail() of  ProcessPensionController");
		return ResponseEntity.ok().body(pensionDetail);
	}

}
