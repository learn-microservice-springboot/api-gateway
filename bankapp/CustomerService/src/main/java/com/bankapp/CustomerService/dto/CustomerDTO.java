package com.bankapp.CustomerService.dto;

import lombok.Data;

@Data
public class CustomerDTO {

	private Long customerId;
	private String customerName;
	private String phoneNumber;
	private String address;

}
