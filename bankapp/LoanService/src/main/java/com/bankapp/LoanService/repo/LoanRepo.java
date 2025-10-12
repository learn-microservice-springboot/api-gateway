package com.bankapp.LoanService.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.LoanService.entity.LoanEntity;

@Repository
public interface LoanRepo extends JpaRepository<LoanEntity, Long> {

	public List<LoanEntity> findByCustomerId(Long customerId);

}
