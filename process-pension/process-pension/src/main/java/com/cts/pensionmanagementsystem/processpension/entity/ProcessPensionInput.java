package com.cts.pensionmanagementsystem.processpension.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Component
@NoArgsConstructor
@AllArgsConstructor
public class ProcessPensionInput {

	@Id
	private long aadhaarNumber;

}

