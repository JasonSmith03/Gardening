package com.farming.garden.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient; // Import Transient
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
    private String moisture; // Keep this as String for JSON input

    @Transient // This field won't be persisted to the database
    private Integer moistureValue; // This will hold the Integer representation

    // Getter for moistureValue to convert moisture String to Integer
    public Integer getMoistureValue() {
        if (moisture != null && moisture.endsWith("%")) {
            try {
                return Integer.parseInt(moisture.replace("%", "").trim());
            } catch (NumberFormatException e) {
                return null; // Return null or handle error
            }
        }
        return null; // Return null if moisture is not formatted correctly
    }
}
