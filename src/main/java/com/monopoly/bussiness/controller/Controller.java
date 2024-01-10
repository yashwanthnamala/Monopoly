package com.monopoly.bussiness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.monopoly.bussiness.entity.Cities;
import com.monopoly.bussiness.entity.Players;
import com.monopoly.bussiness.service.CityService;
import com.monopoly.bussiness.service.PlayerService;

@RestController
public class Controller {

	@Autowired
	CityService cService;

	@Autowired
	PlayerService pService;

	@PostMapping("/addCity")
	public List<Cities> saveCities(@RequestBody List<Cities> cTable) {
		return cService.saveCity(cTable);
	}

	@PostMapping("/start")
	public List<Players> save(@RequestBody List<Players> pTable) {
		return pService.savePerson(pTable);
	}

	@GetMapping("/rollDice")
	public int rollTheDice() {
		return pService.rollDice();
	}

}
