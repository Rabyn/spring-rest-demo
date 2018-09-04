package com.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {

	// Add another exception handler.. to catch Customer Not Found Exception
		// <typeOfResponseBody>...(Exception type to handle/catch)
	
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleExcption(CustomerNotFoundException exc){
		CustomerErrorResponse error = new CustomerErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);		
	}
	
	// Add another exception handler.. to catch any Exception
		// <typeOfResponseBody>...(Exception type to handle/catch)
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleExcption(Exception exc){
		CustomerErrorResponse error = new CustomerErrorResponse(
				HttpStatus.BAD_REQUEST.value(),
				exc.getMessage(),
				System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);		
	}

}
