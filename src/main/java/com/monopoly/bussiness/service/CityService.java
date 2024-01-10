package com.monopoly.bussiness.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monopoly.bussiness.entity.Cities;
import com.monopoly.bussiness.repo.CityRepo;

@Service
public class CityService {

	@Autowired
	CityRepo cityRepo;

	public List<Cities> saveCity(List<Cities> cTable) {
		cityRepo.deleteAll();
		return cityRepo.saveAll(cTable);
	}

}
