package com.farming.garden.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farming.garden.model.PlantsTest;

@Repository
public interface PlantsTestRepository extends JpaRepository<PlantsTest, Long>{

}
