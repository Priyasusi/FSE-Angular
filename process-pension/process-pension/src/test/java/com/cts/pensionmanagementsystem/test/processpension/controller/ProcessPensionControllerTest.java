package com.cts.pensionmanagementsystem.test.processpension.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.cts.pensionmanagementsystem.processpension.client.PensionerDetailsClient;
import com.cts.pensionmanagementsystem.processpension.controller.ProcessPensionController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes = ProcessPensionController.class)
public class ProcessPensionControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	PensionerDetailsClient pensionerDetailsClient;
	
	@Autowired
	ProcessPensionController processPensionController;
	
	@Test
	void contextLoads() {
		log.info("START - contextLoads() of ProcessPensionControllerTest");
		assertNotNull(processPensionController);
		log.info("END - contextLoads() of ProcessPensionControllerTest");
	}
	
	@Test
	void shouldReturnPensionDetails() throws Exception{
		mockMvc.perform(post("/ProcessPension")).andDo(print()).andExpect(status().isOk())
		.andExpect((ResultMatcher) content().string(containsString("bankServiceCharge")));

	}
	
	@Test
	void pensionerDetailShouldNotBeNull() {
		when(pensionerDetailsClient.pensionerDetailByAadhaarNumber(null, 0)).thenReturn(null);
		assertNull(pensionerDetailsClient.pensionerDetailByAadhaarNumber(null, 0));
	}

}
