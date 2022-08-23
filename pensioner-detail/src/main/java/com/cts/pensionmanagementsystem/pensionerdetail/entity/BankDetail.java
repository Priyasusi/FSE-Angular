package com.cts.pensionmanagementsystem.pensionerdetail.entity;

import javax.persistence.Embeddable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Component
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class BankDetail {
	
	private String bankName;
	private long accountNumber;
	private String bankType;
	
}
