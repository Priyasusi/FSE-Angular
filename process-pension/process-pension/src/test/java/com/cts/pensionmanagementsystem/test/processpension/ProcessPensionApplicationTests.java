package com.cts.pensionmanagementsystem.test.processpension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.pensionmanagementsystem.processpension.ProcessPensionApplication;

@SpringBootTest(classes = ProcessPensionApplication.class)
class ProcessPensionApplicationTests {
	
	@Autowired
	ProcessPensionApplication processPensionApplication;

	@Test
	void contextLoads() {
		assertNotNull(processPensionApplication);
	}

}
