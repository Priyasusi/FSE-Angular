package com.cts.pensionmanagementsystem.test.pensionerdetail.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.cts.pensionmanagementsystem.pensionerdetail.controller.PensionerDetailController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest(classes = PensionerDetailController.class)
class PensionerDetailControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	PensionerDetailController pensionerDetailController;

	@Test
	void contextLoads() {
		assertNotNull(pensionerDetailController);
	}

	@Test
	void shouldReturnPensionerDetails() throws Exception {
		log.info("START - shouldReturnPensionerDetails() of PensionerDetailControllerTest");
		mockMvc.perform(get("/PensionerDetailByAadhaar/123456789028")).andDo(print()).andExpect(status().isOk())
				.andExpect((ResultMatcher) content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//	      .andExpect(jsonPath("$[0].name", is("bob")));
		log.info("END - shouldReturnPensionerDetails() of PensionerDetailControllerTest");
	}

}
