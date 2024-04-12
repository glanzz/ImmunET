package com.immunet.immunet.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Pet {
	static enum Gender{
		MALE,
		FEMALE;
	}

	Integer id;
	String name;
	Date dob;
	Gender gender;
	Species species;
	Integer creatorID; 
	
	
	//pet constructor
	public Pet(String name, Date dob, Gender gender, Species s) {
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.species = s;
		 
	}
	
	public boolean isPersisted() {
		return this.id != null;
	}
	
	//saving new pet
	public void save() {}
	
		
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
	

	

}
