package com.bankapp.CardService.controller;

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

import com.bankapp.CardService.dto.CardDTO;
import com.bankapp.CardService.service.CardServiceImpl;

@RestController
@RequestMapping("/api/cards")
public class CardController {

	@Autowired
	private CardServiceImpl cardServiceImpl;

	@GetMapping("/{cardId}")
	public ResponseEntity<CardDTO> getCardDetails(@PathVariable Long cardId) {
		return cardServiceImpl.findCardById(cardId).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());

	}

	@GetMapping
	public ResponseEntity<List<CardDTO>> getCardDetailsByCustomerId(@RequestParam Long customerId) {
		List<CardDTO> customerCards = cardServiceImpl.findCardByCustomerId(customerId);
		if (customerCards != null && !customerCards.isEmpty())
			return ResponseEntity.ok(customerCards);
		else
			return ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<CardDTO> createCard(@RequestBody CardDTO cardReqeust) {
		return ResponseEntity.status(HttpStatus.CREATED).body(cardServiceImpl.createCard(cardReqeust));
	}

}
