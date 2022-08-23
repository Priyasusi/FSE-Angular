package com.cts.pensionmanagementsystem.test.pensionerdetail.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.pensionmanagementsystem.pensionerdetail.repository.PensionerDetailRepository;
import com.cts.pensionmanagementsystem.pensionerdetail.service.PensionerDetailService;

@SpringBootTest
public class PensionerDetailServiceTest {
	
	@Mock
	private PensionerDetailRepository pensionerDetailRepository;
	
	@InjectMocks
	private PensionerDetailService pensionerDetailService;
	
	@Test
	void savePensionerDetailsToDatabaseTest() throws Exception{
		
	}

}
