package com.immunet.immunet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.immunet.immunet.entity.EntitySpecies;
import com.immunet.immunet.entity.VaccineEntity;


public interface VaccineRepository extends JpaRepository<VaccineEntity, Integer>{
	VaccineEntity findDistinctByNameAndSpeciesAndDoctorId(String name, EntitySpecies species, Integer doctorId);
}
