package com.bankapp.CustomerService.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bankapp.CustomerService.dto.CustomerLoanDTO;

@FeignClient(name = "LoanService")
public interface LoanServiceFeignClient {

	@GetMapping("/api/loans")
	public ResponseEntity<List<CustomerLoanDTO>> getLoanDetailsByCustomerId(@RequestParam Long customerId);
}
