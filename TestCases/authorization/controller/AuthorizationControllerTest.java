package com.cts.pensionmanagementsystem.authorization.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cts.pensionmanagementsystem.authorization.entity.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest(classes = AuthorizationController.class)
@ContextConfiguration(classes = AuthorizationController.class)
@RunWith(SpringRunner.class)
public class AuthorizationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Autowired
	AuthorizationController authorizationController;

	@Autowired
	LoginRequest loginRequest;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		loginRequest.setUsername("priya");
		loginRequest.setPassword("priya@123");
	}

	@Test
	void contextLoads() {
		log.info("START - contextLoads() of AuthorizationControllerTest");
		assertNotNull(authorizationController);
		log.info("END - contextLoads() of AuthorizationControllerTest");
	}

	@Test
	void shouldReturnToken() throws Exception {
		log.info("START - shouldReturnToken() of AuthorizationControllerTest");
		String token = authorizationController.generateToken(loginRequest).getBody().getToken();
		log.info("Token:",token);
		assertNotNull(token);
		
//		((ResultActions) ((MockRestRequestMatchers) mockMvc.perform(MockMvcRequestBuilders.post("/Authorize"))).header(HttpHeaders.AUTHORIZATION,token)).andExpect(status().isOk());
				

		log.info("END - shouldReturnToken() of AuthorizationControllerTest");
	}

	@Test
	void shouldReturnTrue() throws Exception {
		log.info("START - shouldReturnTrue() of AuthorizationControllerTest");
		mockMvc.perform(post("/Authorize")).andDo(print()).andExpect(status().isOk())
				.andExpect((ResultMatcher) content().string(containsString("true")));
		log.info("END - shouldReturnTrue() of AuthorizationControllerTest");
	}

	@Test
	void loginTestSuccess() throws Exception {
		log.info("START - loginTestSuccess() of AuthorizationControllerTest");
		LoginRequest defaultUser = new LoginRequest("priya", "priya@123");

		ResultActions resultActions = mockMvc.perform(post("/Authenticate").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(defaultUser)));
		resultActions.andExpect(status().isOk());
		log.info("END - loginTestSuccess() of AuthorizationControllerTest");
	}

	@Test
	void loginTestFail() throws Exception {
		log.info("START - loginTestFail() of AuthorizationControllerTest");
		LoginRequest wrongUser = new LoginRequest("username", "password");

		ResultActions resultActions = mockMvc.perform(post("/Authenticate").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(wrongUser)));
		resultActions.andExpect(status().isNotFound());
		log.info("END - loginTestFail() of AuthorizationControllerTest");
	}

}
