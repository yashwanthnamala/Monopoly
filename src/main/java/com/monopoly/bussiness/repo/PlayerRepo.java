package com.monopoly.bussiness.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monopoly.bussiness.entity.Players;

public interface PlayerRepo extends JpaRepository<Players, Integer> {

	Players findByName(String name);

	Players findBypId(int pId);

	Players findBypIdNot(Integer playerId);
}
