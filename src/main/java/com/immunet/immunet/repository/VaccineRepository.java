package com.immunet.immunet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.immunet.immunet.entity.EntitySpecies;
import com.immunet.immunet.entity.VaccineEntity;


public interface VaccineRepository extends JpaRepository<VaccineEntity, Integer>{
	VaccineEntity findDistinctByNameAndSpeciesAndDoctorId(String name, EntitySpecies species, Integer doctorId);
	List<VaccineEntity> findAllByDoctorId(Integer doctorId);
	List<VaccineEntity> findAllByDoctorIdAndSpecies(Integer doctorId, EntitySpecies species);
	List<VaccineEntity> findAllByDoctorIdAndSpeciesAndIsDefault(Integer doctorId, EntitySpecies species, boolean isDefault);
	
}
