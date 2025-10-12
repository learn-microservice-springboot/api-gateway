package com.bankapp.CustomerService.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorResponseDTO {

	private String message;
	private int status;
	private LocalDateTime timestamp;
	
	public ErrorResponseDTO(String message, int status, LocalDateTime timestamp) {
		this.message=message;
		this.status=status;
		this.timestamp=timestamp;
	}
}
