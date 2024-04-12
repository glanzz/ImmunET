package com.immunet.immunet.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public class CreatePetDTO {
	
	public class CreateOwnerDTO {
		@NotNull
		String name;
		@NotNull
		String address;

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		
	}
	
	@NotNull
	String name;
	
	@NotNull
	String gender;
	
	@NotNull
	String species;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	Date dob;
	
	@NotNull
	CreateOwnerDTO owner;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public CreateOwnerDTO getOwner() {
		return owner;
	}

	public void setOwner(CreateOwnerDTO owner) {
		this.owner = owner;
	}
	
	
	

}
