package com.cts.pensionmanagementsystem.authorization.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import lombok.extern.slf4j.Slf4j;

import org.meanbean.test.BeanTester;

@Slf4j
@SpringBootTest
@ContextConfiguration(loader=AnnotationConfigContextLoader.class,classes = {LoginRequest.class,LoginResponse.class,UserInfo.class})

public class AuthorizationEntitiesTest {

	final BeanTester beanTester = new BeanTester();

	@Test
	void testLoginRequestBean() {
		log.info("START - testLoginRequestBean() of AuthorizationEntitiesTest");
		beanTester.getFactoryCollection();
		beanTester.testBean(LoginRequest.class);
		log.info("END - testLoginRequestBean() of AuthorizationEntitiesTest");
	}

	@Test
	void testLoginResponseBean() {
		log.info("START - testLoginResponseBean() of AuthorizationEntitiesTest");
		beanTester.getFactoryCollection();
		beanTester.testBean(LoginResponse.class);
		log.info("END - testLoginResponseBean() of AuthorizationEntitiesTest");
	}

	@Test
	void testUserInfoBean() {
		log.info("START - testLoginResponseBean() of AuthorizationEntitiesTest");
		beanTester.getFactoryCollection();
		beanTester.testBean(UserInfo.class);
		log.info("END - testUserInfoBean() of AuthorizationEntitiesTest");
	}

}
