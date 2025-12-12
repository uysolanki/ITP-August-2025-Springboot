package com.itp.ITPAugustSpringboot.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> handlePlayerNotFound(ProductNotFoundException ex)
	{ 
			return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<APIError>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
	{ 
		List<APIError> errors = new ArrayList<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) 
		{
		APIError apiError = new APIError(error.getDefaultMessage(), error.getField(), error.getRejectedValue());
		errors.add(apiError);
		}
		return new ResponseEntity<List<APIError>>(errors, HttpStatus.BAD_REQUEST);

	}
}

/*
 [
 {
"errorMessage" : "Title must be between 6 and 100 characters" ,
"fieldName" : "title";
"rejectedValue" : "Au";
 },
  {
"errorMessage" : "Price must be greater than 0" ,
"fieldName" : "price";
"rejectedValue" : "-1";
 }
 ]
 */
