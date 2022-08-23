package com.cts.pensionmanagementsystem.processpension.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.pensionmanagementsystem.pensionerdetail.entity.PensionerDetail;
import com.cts.pensionmanagementsystem.processpension.entity.PensionDetail;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProcessPensionService {

	@Autowired
	PensionDetail pensionDetail;

	
	public PensionDetail calculatePensionDetails(PensionerDetail pensionerDetail) {

		log.info("START - calculatePensionDetails() of  ProcessPensionService");

		double pensionAmount = 0;
		double bankServiceCharge = 0;

		double salary = pensionerDetail.getSalaryEarned();
		double allowances = pensionerDetail.getAllowances();
		String pensionType = pensionerDetail.getPensionType();
		String bankType = pensionerDetail.getBankDetail().getBankType();

		if (pensionType.equalsIgnoreCase("Self") && bankType.equalsIgnoreCase("Public")) {
			pensionAmount = (0.8 * salary) + allowances;
			bankServiceCharge = 500;
		}

		else if (pensionType.equalsIgnoreCase("Self") && bankType.equalsIgnoreCase("Private")) {
			pensionAmount = (0.8 * salary) + allowances;
			bankServiceCharge = 550;
		}

		else if (pensionType.equalsIgnoreCase("Family") && bankType.equalsIgnoreCase("Public")) {
			pensionAmount = (0.5 * salary) + allowances;
			bankServiceCharge = 500;
		} else if (pensionType.equalsIgnoreCase("Family") && bankType.equalsIgnoreCase("Private")) {
			pensionAmount = (0.5 * salary) + allowances;
			bankServiceCharge = 550;
		}

		pensionDetail.setPensionAmount(pensionAmount);
		pensionDetail.setBankServiceCharge(bankServiceCharge);

		log.info("END - calculatePensionDetails() of  ProcessPensionService");

		return pensionDetail;

	}

}
