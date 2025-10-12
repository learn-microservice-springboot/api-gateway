package com.bankapp.LoanService.exception;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.bankapp.LoanService.dto.ErrorResponseDTO;

@ControllerAdvice
public class LoanServicExceptionHandler {

	public static final Logger logger = LoggerFactory.getLogger(LoanServicExceptionHandler.class);

	public ResponseEntity<ErrorResponseDTO> handleAllException(Exception exception) {
		logger.error("Exception encountered", exception);
		return ResponseEntity.internalServerError().body(new ErrorResponseDTO(exception.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now()));

	}
}
