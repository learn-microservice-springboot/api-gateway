package com.bankapp.LoanService.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.LoanService.dto.LoanDTO;
import com.bankapp.LoanService.entity.LoanEntity;
import com.bankapp.LoanService.repo.LoanRepo;

@Service
public class LoanServiceImpl {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private LoanRepo loanRepo;

	public LoanDTO createLoan(LoanDTO loanDTO) {
		LoanEntity loanEntity = modelMapper.map(loanDTO, LoanEntity.class);
		loanEntity = loanRepo.save(loanEntity);
		return modelMapper.map(loanEntity, LoanDTO.class);
	}
	
	public Optional<LoanDTO> findLoanById(Long loanId) {
		
		return loanRepo.findById(loanId).map(loanEntity->modelMapper.map(loanEntity, LoanDTO.class));
	}

	public List<LoanDTO> findLoanCustomerId(Long customerId) {
		 List<LoanEntity> loanEntityList= loanRepo.findByCustomerId(customerId);
		 List<LoanDTO> list = loanEntityList.stream().map(x-> modelMapper.map(x, LoanDTO.class)).toList();
		 return list;
	}

}
