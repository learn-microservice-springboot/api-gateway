package com.bankapp.CustomerService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.CustomerService.entity.CustomerEntity;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity, Long> {

}
