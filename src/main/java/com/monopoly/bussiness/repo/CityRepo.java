package com.monopoly.bussiness.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monopoly.bussiness.entity.Cities;

public interface CityRepo extends JpaRepository<Cities, Integer> {

	Cities findBycId(Integer cId);
}
