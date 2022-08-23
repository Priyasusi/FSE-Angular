package com.cts.pensionmanagementsystem.test.processpension.client;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.pensionmanagementsystem.pensionerdetail.entity.PensionerDetail;
import com.cts.pensionmanagementsystem.processpension.client.PensionerDetailsClient;

@SpringBootTest
public class PensionerDetailClientTest {
	
	@Mock
	PensionerDetailsClient pensionerDetailsClient;
	
	@Test
	void pensionerDetailByAadhaarNumberTest() {
		assertFalse(pensionerDetailsClient.pensionerDetailByAadhaarNumber(null, 0).equals(new PensionerDetail() ));
	}

}
