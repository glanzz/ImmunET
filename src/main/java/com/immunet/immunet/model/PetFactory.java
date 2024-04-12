package com.immunet.immunet.model;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.immunet.immunet.entity.PetEntity;
import com.immunet.immunet.model.Pet.Gender;
import com.immunet.immunet.service.PetService;


@Component
public class PetFactory {
	@Autowired
	PetService service;
	
	public Pet getPet() {
		return new Pet(service);
	}
	
	public Pet getPet(String name, Date dob, Gender gender, Species s, Integer creatorId) {
		return new Pet(service, name, dob, gender, s, creatorId);
	}
	public Pet getPet(PetEntity p) {
		Pet pet = new Pet(service);
		pet.load(p);
		return pet;
	}

}
