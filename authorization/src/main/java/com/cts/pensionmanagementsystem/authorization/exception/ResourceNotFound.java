package com.cts.pensionmanagementsystem.authorization.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResourceNotFound extends RuntimeException{

	private static final long serialVersionUID = 2054721071909492669L;
	private final String message;
	
}
