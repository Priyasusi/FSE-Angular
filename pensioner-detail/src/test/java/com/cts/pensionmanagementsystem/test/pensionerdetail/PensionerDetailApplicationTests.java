package com.cts.pensionmanagementsystem.test.pensionerdetail;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.pensionmanagementsystem.pensionerdetail.PensionerDetailApplication;

@SpringBootTest(classes = PensionerDetailApplication.class)
class PensionerDetailApplicationTests {
	
	@Autowired
	PensionerDetailApplication pensionerDetailApplication;

	@Test
	void contextLoads() {
		assertNotNull(pensionerDetailApplication);
	}

}
