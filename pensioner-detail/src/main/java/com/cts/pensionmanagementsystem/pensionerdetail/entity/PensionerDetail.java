package com.cts.pensionmanagementsystem.pensionerdetail.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Component
@NoArgsConstructor
@AllArgsConstructor
public class PensionerDetail {

	@Id
	private long aadhaarNumber; 
	private String pensionerName;
	private String dateOfBirth;
	private String permenantAccountNumber;
	private double salaryEarned;
	private double allowances;
	private String pensionType;
	
	@Embedded
	@Autowired
	private BankDetail bankDetail;

}
