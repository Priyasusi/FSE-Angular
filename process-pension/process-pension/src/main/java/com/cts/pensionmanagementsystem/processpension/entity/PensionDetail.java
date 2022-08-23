package com.cts.pensionmanagementsystem.processpension.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

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
public class PensionDetail {
	
	private double bankServiceCharge;
	@Id
	private double pensionAmount;

}
