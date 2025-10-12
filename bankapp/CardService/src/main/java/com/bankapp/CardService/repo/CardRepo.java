package com.bankapp.CardService.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.CardService.entity.CardEntity;

@Repository
public interface CardRepo extends JpaRepository<CardEntity,Long> {

	public List<CardEntity> findByCustomerId(Long customerId);

}
