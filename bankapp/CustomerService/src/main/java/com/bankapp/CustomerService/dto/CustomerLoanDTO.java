package com.bankapp.CustomerService.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CustomerLoanDTO {

	private Long loanId;
	private Long customerId;
	private BigDecimal loanAmount;
	private LoanType loanType;

	public enum LoanType {
		AUTO, HOME, PERSONAL, EDUCATION
	}

}
