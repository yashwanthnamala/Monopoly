package com.monopoly.bussiness.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monopoly.bussiness.entity.Players;
import com.monopoly.bussiness.repo.CityRepo;
import com.monopoly.bussiness.repo.PlayerRepo;

@Service
public class PlayerService {

	@Autowired
	PlayerRepo playerRepo;

	@Autowired
	CityRepo cityRepo;

	public List<Players> savePerson(List<Players> person) {
		playerRepo.deleteAll();
		return playerRepo.saveAll(person);
	}

	public int rollDice() {
		Random random = new Random();
		System.out.println(cityRepo.count());
		return random.nextInt(6) + 1;
	}

}
