package com.farming.garden.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farming.garden.model.PlantsTestEntity;
import com.farming.garden.service.PlantService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/garden/v1")
@RequiredArgsConstructor
@Validated
@Slf4j
public class GardenController {
	private final PlantService plantService;
	
	@GetMapping
	public ResponseEntity<List<PlantsTestEntity>> getAllPlants(){
		return ResponseEntity.ok().body(plantService.findAllPlants());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PlantsTestEntity> getPlantById(@PathVariable("id") Long id){
		log.info("GET CALL WITH ID {}", id);
		return ResponseEntity.ok().body(plantService.findPlantById(id));
	}
	
	@PostMapping("/add")
	public ResponseEntity<PlantsTestEntity> savePlant(@RequestBody PlantsTestEntity plantsTest){
		return ResponseEntity.ok().body(plantService.savePlantsTest(plantsTest));
	}
	
	@PutMapping
	public ResponseEntity<PlantsTestEntity> updatePlant(@RequestBody PlantsTestEntity plantsTest){
		return ResponseEntity.ok().body(plantService.updatePlantsTest(plantsTest));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePlantById(@PathVariable("id") Long id){
		plantService.deletePlantById(id);
		return ResponseEntity.ok().body("Deleted plant successfully");
	}
}
