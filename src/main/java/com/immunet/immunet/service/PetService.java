package com.immunet.immunet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immunet.immunet.dao.PetDAO;
import com.immunet.immunet.entity.EntityGender;
import com.immunet.immunet.entity.EntitySpecies;
import com.immunet.immunet.entity.PetEntity;
import com.immunet.immunet.model.ImmunizationReport;
import com.immunet.immunet.model.Owner;
import com.immunet.immunet.model.Pet;

import jakarta.transaction.Transactional;

@Service
public class PetService {
	
	@Autowired
	private PetDAO petDAO;
	
	@Transactional
	public Integer save(Pet p, Integer ownerId, Integer creatorId) {
		PetEntity pet = new PetEntity();
		pet.setName(p.getName());
		pet.setDob(p.getDob());
		pet.setGender(EntityGender.valueOf(p.getGender().name()));
		pet.setSpecies(EntitySpecies.valueOf(p.getSpecies().name()));
		pet.setOwnerId(ownerId);
		pet.setCreatedBy(creatorId);
		petDAO.save(pet);
		return pet.getId();
	}
	
	@Transactional
	public void petCreation(Integer userId, Owner petOwner, Pet pet, ImmunizationReport report) {
		System.out.println(userId);
		petOwner.save(userId);
		pet.save(petOwner.getId(), userId);
		report.save(userId);
	}
}
