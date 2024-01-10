package com.monopoly.bussiness.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Players {

	@Id
	private int pId;
	private String name;
	private double balance;
	private int position;

	@OneToMany(mappedBy = "owner")
	private List<Cities> ownedCities;

}