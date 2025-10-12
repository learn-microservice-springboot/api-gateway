package com.bankapp.CustomerService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.bankapp.CustomerService.dto.CustomerDTO;
import com.bankapp.CustomerService.dto.CustomerSummaryDTO;
import com.bankapp.CustomerService.service.CustomerServiceImpl;

@RestController
@RequestMapping("/api/customer")
public class CustomerServiceController {

	@Autowired
	CustomerServiceImpl customerServiceImpl;

	/*IMPLEMENT OPEN FIEGN TO GET DATA FORM OTHER MNICROSERVICE*/
	@GetMapping("/summary/{customerId}")
	public CustomerSummaryDTO getCustomerSummary(@PathVariable Long customerId) throws NoResourceFoundException {
	
		return	customerServiceImpl.getCustomerSummary(customerId);
	}
	
	
	@PostMapping
	public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(customerServiceImpl.createCustomer(customerDTO));
	}

}
