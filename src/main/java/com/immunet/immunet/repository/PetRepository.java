package com.immunet.immunet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.immunet.immunet.model.Pet;


public interface PetRepository extends JpaRepository<Pet, Integer>{

}
