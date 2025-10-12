package com.bankapp.CustomerService.dto;

import java.time.LocalDate;

import lombok.Data;
@Data
public class CustomerCardDTO {

	private String cardNumber;
	private Long customerId;
	private Long cardId;
	private cardType cardType;
	private int cvv;
	private LocalDate expiryDate;

	public enum cardType {
		DEBIT_CARD, CREDIT_CARD
	};

}
