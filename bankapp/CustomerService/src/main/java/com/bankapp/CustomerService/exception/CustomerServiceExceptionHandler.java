package com.bankapp.CustomerService.exception;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bankapp.CustomerService.dto.ErrorResponseDTO;

@ControllerAdvice
public class CustomerServiceExceptionHandler {

	public static final  Logger logger= LoggerFactory.getLogger(CustomerServiceExceptionHandler.class);
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDTO> handleAllExceptions(Exception exception)
	{
		logger.error("Exception encountered",exception);
		return ResponseEntity.internalServerError().body(new ErrorResponseDTO(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now()));
		
	}
}
