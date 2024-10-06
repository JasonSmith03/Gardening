package com.farming.garden.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.farming.garden.model.PlantsTest;
import com.farming.garden.repository.PlantsTestRepository;

@Service
@Slf4j
public class PlantService {
    
    @Autowired
    private PlantsTestRepository plantsTestRepository;
    
    // Method to convert moisture from String to Integer
    private Integer convertMoisture(String moisture) {
        if (moisture != null && moisture.endsWith("%")) {
            try {
                // Parse the string to an Integer after removing the '%'
                return Integer.parseInt(moisture.replace("%", "").trim());
            } catch (NumberFormatException e) {
                log.warn("Failed to convert moisture value: {}", moisture, e);
            }
        }
        return null; // Return null if conversion fails
    }
    
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
        // Log moisture value before conversion
        log.info("Received moisture value: {}", plantsTest.getMoisture());
        
        // The moisture remains as String in the entity
        // You can convert it when necessary for other logic
        Integer convertedMoisture = convertMoisture(plantsTest.getMoisture());
        
        // Here you can use the convertedMoisture as needed
        log.info("Converted moisture value: {}", convertedMoisture);
        
        // Save the entity
        PlantsTest savedPlantsTest = plantsTestRepository.save(plantsTest);
        
        log.info("Plant with id: {} saved successfully", plantsTest.getPlants_id());
        return savedPlantsTest;
    }
    
    public PlantsTest updatePlantsTest(PlantsTest plantsTest) {
        Optional<PlantsTest> existingPlantTestOptional = plantsTestRepository.findById(plantsTest.getPlants_id());
        
        if (existingPlantTestOptional.isPresent()) {
            PlantsTest existingPlantsTest = existingPlantTestOptional.get();
            
            if (plantsTest.getPlants_name() != null) {
                existingPlantsTest.setPlants_name(plantsTest.getPlants_name());
            }
            if (plantsTest.getPlants_species() != null) {
                existingPlantsTest.setPlants_species(plantsTest.getPlants_species());
            }
            if (plantsTest.getPlants_last_watered() != null) {
                existingPlantsTest.setPlants_last_watered(plantsTest.getPlants_last_watered());
            }
            if (plantsTest.getMoisture() != null) {
                Integer convertedMoisture = convertMoisture(plantsTest.getMoisture());
                existingPlantsTest.setMoisture(plantsTest.getMoisture()); // Keep moisture as String
                log.info("Updated moisture value: {}", plantsTest.getMoisture());
            }

            PlantsTest updatedPlantsTest = plantsTestRepository.save(existingPlantsTest);
            log.info("Plant with id: {} updated successfully", plantsTest.getPlants_id());
            return updatedPlantsTest;
        }
        log.info("Plant with id {} was not found", plantsTest.getPlants_id());
        return null;
    }
    
    public void deletePlantById(Long id) {
        plantsTestRepository.deleteById(id);
    }
}
