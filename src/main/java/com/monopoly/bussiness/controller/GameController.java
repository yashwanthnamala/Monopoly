package com.monopoly.bussiness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monopoly.bussiness.service.GameService;

@RestController
public class GameController {

	@Autowired
	GameService gService;

	@PostMapping("/player-turn")
	public ResponseEntity<?> playTurn(@RequestParam int playerId) {
		if (playerId == 1) {
			return gService.playTurn(1);
		} else if (playerId == 2) {
			return gService.playTurn(2);
		} else {
			return ResponseEntity.badRequest().body("Invalid player name: " + playerId);
		}
	}

	@GetMapping("/resume-game")
	public ResponseEntity<?> resumeGame() {
		return gService.resumeGame();
	}
}
