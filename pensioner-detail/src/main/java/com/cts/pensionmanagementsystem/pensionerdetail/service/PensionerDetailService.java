package com.cts.pensionmanagementsystem.pensionerdetail.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.pensionmanagementsystem.pensionerdetail.entity.BankDetail;
import com.cts.pensionmanagementsystem.pensionerdetail.entity.PensionerDetail;
import com.cts.pensionmanagementsystem.pensionerdetail.repository.PensionerDetailRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PensionerDetailService {

	@Autowired
	PensionerDetail pensionerDetail = null;

	@Autowired
	PensionerDetailRepository pensionerDetailRepository;

	@PostConstruct
	public void savePensionerDetailsToDatabase() throws IOException {

		log.info("START - savePensionerDetailsToDatabase() of PensionerDetailService");

		var bufferreaderData = "";

		List<PensionerDetail> pensionerDetailsList = new ArrayList<>();
		
		var fileReader = new FileReader("src/main/resources/PensionerDetails.csv");
		var  bufferedReader = new BufferedReader(fileReader);

		try {
			
			while ((bufferreaderData = bufferedReader.readLine()) != null) {
				String[] pensionerData = bufferreaderData.split(",");

				var bankDetail = new BankDetail();
				bankDetail.setBankName(pensionerData[7]);
				bankDetail.setAccountNumber(Long.parseLong(pensionerData[8]));
				bankDetail.setBankType(pensionerData[9]);

				pensionerDetail = new PensionerDetail();
				pensionerDetail.setAadhaarNumber(Long.parseLong(pensionerData[0]));
				pensionerDetail.setPensionerName(pensionerData[1]);
				pensionerDetail.setDateOfBirth(pensionerData[2]);
				pensionerDetail.setPermenantAccountNumber(pensionerData[3]);
				pensionerDetail.setSalaryEarned(Double.parseDouble(pensionerData[4]));
				pensionerDetail.setAllowances(Double.parseDouble(pensionerData[5]));
				pensionerDetail.setPensionType(pensionerData[6]);
				pensionerDetail.setBankDetail(bankDetail);

				log.info("New Pensioner Added: " + pensionerDetail.toString());

				pensionerDetailsList.add(pensionerDetail);
			}

			
		}

		catch (FileNotFoundException e) {
			log.error("FileNotFoundException in savePensionerDetailsToDatabase() " + e);
		} catch (IOException e) {
			log.error("IOException in savePensionerDetailsToDatabase() " + e);
		}
		finally {
			bufferedReader.close();
		}

		pensionerDetailRepository.saveAll(pensionerDetailsList);

		log.info("END - savePensionerDetailsToDatabase() of PensionerDetailService");
	}

	public PensionerDetail getPensionerDetailsByAadhaar(long aadhaarNumber) {

		log.info("START - getPensionerDetailsByAadhaar() of PensionerDetailService");

		try {
			pensionerDetail = pensionerDetailRepository.findByAadhaarNumber(aadhaarNumber);

			if (pensionerDetail == null) {
				log.error("No Pensioner Detail found for the given Aadhaar Number.");
				throw new NullPointerException(
						"findByAadhaarNumber() returned null for the Aadhaar Number " + aadhaarNumber);
			}

		} catch (Exception e) {
			log.error("Exception occurred in getPensionerDetailsByAadhaar()" + e);
		}

		log.info("END - getPensionerDetailsByAadhaar() of PensionerDetailService");

		return pensionerDetail;
	}

}
