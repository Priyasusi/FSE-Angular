package com.cts.pensionmanagementsystem.authorization.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Component
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest implements Serializable {

	private static final long serialVersionUID = 888187267273531022L;
	private String username;
	private String password;

}
