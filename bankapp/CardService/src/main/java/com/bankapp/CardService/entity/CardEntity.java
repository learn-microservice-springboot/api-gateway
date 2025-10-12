package com.bankapp.CardService.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
public class CardEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
