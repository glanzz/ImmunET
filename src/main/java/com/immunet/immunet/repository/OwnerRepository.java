package com.immunet.immunet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.immunet.immunet.entity.EntitySpecies;
import com.immunet.immunet.entity.OwnerEntity;
import com.immunet.immunet.entity.VaccineEntity;


public interface OwnerRepository extends JpaRepository<OwnerEntity, Integer>{
	OwnerEntity findDistinctByNameAndAddress(String name, String address);
}
