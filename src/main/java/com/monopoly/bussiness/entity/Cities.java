package com.monopoly.bussiness.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cities {

	@Id
	private int cId;
	private String cityName;
	private Double price;
	private Double rent;
	private boolean owned;
	
	@ManyToOne
    private Players owner;
	

}
