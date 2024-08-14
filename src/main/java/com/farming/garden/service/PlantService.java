package com.farming.garden.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import com.farming.garden.model.PlantsTest;
import com.farming.garden.repository.PlantsTestRepository;

import lombok.extern.java.Log;

@Service
@Slf4j
public class PlantService {
	
	@Autowired
	private PlantsTestRepository plantsTestRepository;
	
	
	public List<PlantsTest> findAllPlants(){		
		return plantsTestRepository.findAll();
	}
	
	public PlantsTest findPlantById(Long id) {
		Optional<PlantsTest> plantsTestOptional = plantsTestRepository.findById(id);
		if(plantsTestOptional.isPresent()) {
			return plantsTestOptional.get();
		}
		
		log.info("id {} was not found", id);
		return null;
	}
	
	public PlantsTest savePlantsTest(PlantsTest plantsTest) {
		 PlantsTest savedPlantsTest = plantsTestRepository.save(plantsTest);
		 
		 log.info("Plant with id: {} saved successsfully", plantsTest.getPlants_id());
		 return savedPlantsTest;
	}
	
	public PlantsTest updatePlantsTest(PlantsTest plantsTest) {
		Optional<PlantsTest> existingPlantTestOptional = plantsTestRepository.findById(plantsTest.getPlants_id());
		
		if(existingPlantTestOptional.isPresent()) {
			PlantsTest existingPlantsTest = existingPlantTestOptional.get();
			
			if(plantsTest.getPlants_name() != null) {
				existingPlantsTest.setPlants_name(plantsTest.getPlants_name());
			}
			if(plantsTest.getPlants_name() != null) {
				existingPlantsTest.setPlants_species(plantsTest.getPlants_species());
			}
			if(plantsTest.getPlants_name() != null) {
				existingPlantsTest.setPlants_last_watered(plantsTest.getPlants_last_watered());
			}
			
			PlantsTest updatedPlantsTest = plantsTestRepository.save(existingPlantsTest);
			log.info("Plant wiht id: {} updated successfully", plantsTest.getPlants_id());
			return updatedPlantsTest;
		}
		log.info("Plant with id {} was not found", plantsTest.getPlants_id());
		return null;
			
	}
	
	public void deletePlantById(Long id) {
		plantsTestRepository.deleteById(id);
	}

}
