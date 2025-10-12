package com.bankapp.CardService.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.CardService.dto.CardDTO;
import com.bankapp.CardService.entity.CardEntity;
import com.bankapp.CardService.repo.CardRepo;

@Service
public class CardServiceImpl {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CardRepo cardRepo;

	public CardDTO createCard(CardDTO cardRequest) {
		CardEntity cardEntity = modelMapper.map(cardRequest, CardEntity.class);
		cardRepo.save(cardEntity);
		return modelMapper.map(cardEntity, CardDTO.class);
	}

	public Optional<CardDTO> findCardById(Long cardId) {
		return cardRepo.findById(cardId).map(cardEntity -> modelMapper.map(cardEntity, CardDTO.class));
	}

	public List<CardDTO> findCardByCustomerId(Long customerId) {
		return cardRepo.findByCustomerId(customerId).stream()
				.map(cardEntity -> modelMapper.map(cardEntity, CardDTO.class)).toList();
	}

}
