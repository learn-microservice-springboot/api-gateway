package com.bankapp.LoanService.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class LoanDTO {

	private Long loanId;
	private Long customerId;
	private BigDecimal loanAmount;
	private LoanType loanType;

	public enum LoanType {
		AUTO, HOME, PERSONAL, EDUCATION
	}

}
