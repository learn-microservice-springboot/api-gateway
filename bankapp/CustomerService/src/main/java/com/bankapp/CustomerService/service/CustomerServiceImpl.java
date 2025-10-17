package com.bankapp.CustomerService.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.bankapp.CustomerService.client.CardServiceFeignClient;
import com.bankapp.CustomerService.client.LoanServiceFeignClient;
import com.bankapp.CustomerService.dto.CustomerDTO;
import com.bankapp.CustomerService.dto.CustomerSummaryDTO;
import com.bankapp.CustomerService.entity.CustomerEntity;
import com.bankapp.CustomerService.repo.CustomerRepo;
import com.bankapp.CustomerService.util.ICustomerServiceConstants;

@Service
public class CustomerServiceImpl {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	CustomerRepo customerRepo;

	@Autowired
	LoanServiceFeignClient loanServiceFeignClient;

	@Autowired
	CardServiceFeignClient cardServiceFeignClient;
	
	private static final Logger logger= LoggerFactory.getLogger(CustomerServiceImpl.class);

	public CustomerDTO createCustomer(CustomerDTO customerDTO) {
		logger.debug("[{}:{}] Inside CustomerServiceController.createCustomer",ICustomerServiceConstants.CorrelationId,MDC.get(ICustomerServiceConstants.CorrelationId));
		CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
		customerEntity = customerRepo.save(customerEntity);
		logger.debug("[{}:{}] CustomerId:{} created",ICustomerServiceConstants.CorrelationId,MDC.get(ICustomerServiceConstants.CorrelationId),customerEntity.getCustomerId());
		return modelMapper.map(customerEntity, CustomerDTO.class);

	}

	public CustomerSummaryDTO getCustomerSummary(Long customerId) throws NoResourceFoundException {
		CustomerSummaryDTO customerSumamryDto = new CustomerSummaryDTO();
		customerSumamryDto.setCustomerDTO(modelMapper.map(
				customerRepo.findById(customerId).orElseThrow(
						() -> new NoResourceFoundException(HttpMethod.GET, "api/customer/summary/" + customerId)),
				CustomerDTO.class));
		customerRepo.findById(customerId).ifPresent(customerEntity -> customerSumamryDto
				.setCustomerDTO(modelMapper.map(customerEntity, CustomerDTO.class)));
		customerSumamryDto.setCustomerLoans(loanServiceFeignClient.getLoanDetailsByCustomerId(customerId).getBody());
		customerSumamryDto.setCustomerCards(cardServiceFeignClient.getCardDetailsByCustomerId(customerId).getBody());
		return customerSumamryDto;
	}
}
