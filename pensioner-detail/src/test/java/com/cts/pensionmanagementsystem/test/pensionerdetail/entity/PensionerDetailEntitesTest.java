package com.cts.pensionmanagementsystem.test.pensionerdetail.entity;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

import com.cts.pensionmanagementsystem.pensionerdetail.entity.BankDetail;
import com.cts.pensionmanagementsystem.pensionerdetail.entity.PensionerDetail;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PensionerDetailEntitesTest {
	
	final BeanTester beanTester = new BeanTester();

	@Test
	void testBankDetailBean() {
		log.info("START - testAuthorizeResponseBean() of AuthorizationEntitiesTest");
		beanTester.getFactoryCollection();
		beanTester.testBean(BankDetail.class);
		log.info("END - testAuthorizeResponseBean() of AuthorizationEntitiesTest");
	}
	
	@Test
	void testPensionerDetailBean() {
		log.info("START - testAuthorizeResponseBean() of AuthorizationEntitiesTest");
		beanTester.getFactoryCollection();
		beanTester.testBean(PensionerDetail.class);
		log.info("END - testAuthorizeResponseBean() of AuthorizationEntitiesTest");
	}

}
