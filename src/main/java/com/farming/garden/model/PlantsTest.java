package com.farming.garden.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "plants_test")
public class PlantsTest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long plants_id;
	private String plants_name;
	private String plants_species;
	private Timestamp plants_last_watered;

}
