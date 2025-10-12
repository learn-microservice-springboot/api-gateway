package com.bankapp.CustomerService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerId;
	private String customerName;
	private String phoneNumber;
	private String address;

}
