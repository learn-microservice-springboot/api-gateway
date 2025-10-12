package com.bankapp.CustomerService.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bankapp.CustomerService.dto.CustomerCardDTO;

@FeignClient("CardService")
public interface CardServiceFeignClient {

	@GetMapping("/api/cards")
	public ResponseEntity<List<CustomerCardDTO>> getCardDetailsByCustomerId(@RequestParam Long customerId);

}
