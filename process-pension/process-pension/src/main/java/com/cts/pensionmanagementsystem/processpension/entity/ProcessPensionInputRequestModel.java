package com.cts.pensionmanagementsystem.processpension.entity;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Component
@NoArgsConstructor
@AllArgsConstructor
public class ProcessPensionInputRequestModel {
	
	private long aadhaarNumber;

}
