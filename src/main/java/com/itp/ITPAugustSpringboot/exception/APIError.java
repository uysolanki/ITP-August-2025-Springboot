package com.itp.ITPAugustSpringboot.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class APIError {
	
	String errorMessage;
	String fieldName;
	Object rejectedValue;

}
