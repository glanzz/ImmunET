package com.immunet.immunet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.immunet.immunet.entity.PetEntity;


public interface PetRepository extends JpaRepository<PetEntity, Integer>{
	

}
