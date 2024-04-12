package com.immunet.immunet.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.immunet.immunet.entity.PetEntity;
import com.immunet.immunet.service.PetService;


public class Pet {
	public static enum Gender{
		MALE,
		FEMALE;
	}

	Integer id;
	String name;
	Date dob;
	Gender gender;
	Species species;
	Integer creatorID;
	Integer ownerId;
	PetService service;
	
	
	//pet constructor
	public Pet(PetService service, String name, Date dob, Gender gender, Species s, Integer creatorId) {
		this.service = service;
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.species = s;
		this.creatorID = creatorId;
		 
	}
	
	public Pet(PetService service) {
		this.service = service;
	}
	
	public boolean isPersisted() {
		return this.id != null;
	}
	
	//saving new pet
	public void save(Integer ownerId, Integer creatorId) {
		if(isPersisted() ) {
			return;
		}
		this.id = service.save(this, ownerId, creatorId);
		
	}
	
	
	public void load(PetEntity pet) {
		this.id = pet.getId();
		this.setCreatorID(pet.getCreator().getId());
		this.setName(pet.getName());
		this.setDob(pet.getDob());
		this.setGender(Gender.valueOf(pet.getGender().name()));
		this.setOwnerId(pet.getOwnerId());
		this.setSpecies(Species.valueOf(pet.getSpecies().name()));
	}
	
		
	//getters and setters
	public Species getSpecies() {
		return species;
	}
	public void setSpecies(Species s) {
		this.species = s;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Integer getCreatorID() {
		return creatorID;
	}

	public void setCreatorID(Integer creatorID) {
		this.creatorID = creatorID;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	

	

}
