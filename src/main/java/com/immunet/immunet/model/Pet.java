package com.immunet.immunet.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Pet {
	static enum Gender{
		MALE,
		FEMALE;
	}

	int id;
	String name;
	Date dob;
	Gender gender;
	Species species;
	
	
	//pet constructor
	public Pet(String name, Date dob, Gender gender, Species s) {
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.species = s;
		 
	}
	
	//saving new pet
	public void save() {}
	
	public boolean immunizationReportExists() {
		return false;
	}
	
	public List<ShotRecord> getShotRecords() {
		//Need to mention getShotsDTO list name to return as a list in this method
		List<ShotRecord> shotRecords = new ArrayList<ShotRecord>();
		if(immunizationReportExists()) {
			return null;
				
		} else {
			List<Vaccine> defaultVaccines = new ArrayList<Vaccine>();
			for(Vaccine defaultVaccine: defaultVaccines) {
				shotRecords.add(defaultVaccine.getShotRecord(this.dob));
				
			}
		}
		return shotRecords;//(getShotsDTO) this already return a list so need to pass it here
	}
		
	//getters and setters
	public Species getS() {
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

	

}
