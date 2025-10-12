package com.bankapp.CustomerService.dto;

import java.util.List;

import lombok.Data;

@Data
public class CustomerSummaryDTO {

	private CustomerDTO customerDTO;

	private List<CustomerLoanDTO> customerLoans;

	private List<CustomerCardDTO> customerCards;

}
