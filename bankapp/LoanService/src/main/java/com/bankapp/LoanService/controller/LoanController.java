package com.bankapp.LoanService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.LoanService.dto.LoanDTO;
import com.bankapp.LoanService.service.LoanServiceImpl;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

	@Autowired
	LoanServiceImpl loanServiceImpl;

	@GetMapping("/{loanId}")
	public ResponseEntity<LoanDTO> getLoanDetails(@PathVariable Long loanId) {
		return loanServiceImpl.findLoanById(loanId).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());

	}
	
	@GetMapping
	public ResponseEntity<List<LoanDTO>> getLoanDetailsByCustomerId(@RequestParam Long customerId) {
		 List<LoanDTO> loans = loanServiceImpl.findLoanCustomerId(customerId);
		 if(loans!=null&& !loans.isEmpty())
			 return ResponseEntity.ok(loans);
		 else
			 return ResponseEntity.noContent().build();
		 
	}
	
	

	@PostMapping
	public ResponseEntity<LoanDTO> createLoan(@RequestBody LoanDTO loanDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(loanServiceImpl.createLoan(loanDTO));

	}

}
