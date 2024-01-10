package com.monopoly.bussiness.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.monopoly.bussiness.entity.Cities;
import com.monopoly.bussiness.entity.Players;
import com.monopoly.bussiness.repo.CityRepo;
import com.monopoly.bussiness.repo.PlayerRepo;

@Service
public class GameService {

	@Autowired
	private CityRepo cityRepo;

	@Autowired
	private PlayerRepo playerRepo;

	private Integer lastPlayerId;

	// Method to generate random number
	public int rollDice() {
		Random random = new Random();
		return random.nextInt(6) + 1;
	}

	// Method to move player on the number generated
	public String movePlayer(Players player, int steps) {
		int currentPosition = player.getPosition();
		long totalCities = cityRepo.count();
		System.out.println(totalCities);
		if (currentPosition + steps >= totalCities) {
			double newBal = player.getBalance() + 2000;
			player.setBalance(newBal);
		}
		int newPosition = (int) ((currentPosition + steps) % totalCities + 1);
		player.setPosition(newPosition);
		playerRepo.save(player);
		return "Player " + player.getName() + " moved to position " + player.getPosition();
	}

	// Method to buy or pay rent for the place
	public ResponseEntity<?> playTurn(Integer playerId) {

		if (isTheSamePlayerPlaying(playerId, lastPlayerId)) {
			return ResponseEntity.ok("It's not your turn");
		}

		Players currentPlayer = playerRepo.findBypId(playerId);
//		Players opponentPlayer = playerRepo.findBypIdNot(playerId);

		int diceResult = rollDice();
		String moveResult = movePlayer(currentPlayer, diceResult);

		Cities currentCity = cityRepo.findBycId(currentPlayer.getPosition());

		ResponseEntity<?> response;

		if (!currentCity.isOwned()) {
			Double price = currentCity.getPrice();
			if (currentPlayer.getBalance() >= price) {
				currentPlayer.setBalance(currentPlayer.getBalance() - price);
				playerRepo.save(currentPlayer);

				currentCity.setOwned(true);
				currentCity.setOwner(currentPlayer);
				cityRepo.save(currentCity);

				response = ResponseEntity.ok(moveResult + " " + " bought " + currentCity.getCityName() + " for " + price
						+ ". Balance updated: " + currentPlayer.getBalance());
			} else {
				response = ResponseEntity.ok(moveResult + " " + " doesn't have enough balance to buy "
						+ currentCity.getCityName() + ". Balance: " + currentPlayer.getBalance());
			}
		} else {
			Players ownerPlayer = currentCity.getOwner();
			if (!currentPlayer.equals(ownerPlayer)) {
				if (currentCity.getRent() > currentPlayer.getBalance()) {
					response = ResponseEntity.ok("You ran out of balance. Your Game is Over");
				} else {
					Double rent = currentCity.getRent();
					currentPlayer.setBalance(currentPlayer.getBalance() - rent);
					playerRepo.save(currentPlayer);

					ownerPlayer.setBalance(ownerPlayer.getBalance() + rent);
					playerRepo.save(ownerPlayer);

					response = ResponseEntity
							.ok(moveResult + " " + currentPlayer.getName() + " is at " + currentCity.getCityName()
									+ " which is owned by " + ownerPlayer.getName() + " pay rent of " + rent);
				}
			} else {
				response = ResponseEntity
						.ok(moveResult + " " + currentPlayer.getName() + " owns " + currentCity.getCityName());
			}
		}
		lastPlayerId = playerId;

		return response;
	}

	// Method to check the turn of the player
	private boolean isTheSamePlayerPlaying(Integer currentPlayerId, Integer lastPlayerId) {
		return currentPlayerId.equals(lastPlayerId);
	}

	// Method to resume the game
	public ResponseEntity<?> resumeGame() {

		List<Players> players = playerRepo.findAll();

		StringBuilder response = new StringBuilder();

		for (Players player : players) {
			response.append("Player ").append(player.getPId()).append(" ").append(player.getName())
					.append(" is positioned at ").append(player.getPosition()).append(" with a balance of ")
					.append(player.getBalance()).append("\n");
		}

		return ResponseEntity.ok(response);
	}

}
