package com.bankapp.CardService.dto;

import java.time.LocalDate;

import lombok.Data;
@Data
public class CardDTO {

	private Long cardId;
	private Long customerId;
	private String cardNumber;
	private cardType cardType;
	private int cvv;
	private LocalDate expiryDate;

	public enum cardType {
		DEBIT_CARD, CREDIT_CARD
	};

}
