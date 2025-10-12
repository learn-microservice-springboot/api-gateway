package com.bankapp.LoanService.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class LoanEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long loanId;
	private Long customerId;
	private BigDecimal loanAmount;
	private LoanType loanType;

	public enum LoanType {
		AUTO, HOME, PERSONAL, EDUCATION
	}

}
