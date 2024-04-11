package com.immunet.immunet.dto;

import jakarta.validation.constraints.NotNull;

public class CreatePetDTO {
	
	public class CreateOwnerDTO {
		@NotNull
		String name;
		@NotNull
		String gender;
		@NotNull
		String contact;
		@NotNull
		String address;

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
		public String getContact() {
			return contact;
		}
		public void setContact(String contact) {
			this.contact = contact;
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
	String dob;
	
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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public CreateOwnerDTO getOwner() {
		return owner;
	}

	public void setOwner(CreateOwnerDTO owner) {
		this.owner = owner;
	}
	
	
	

}
