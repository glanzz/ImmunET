package com.immunet.immunet.model;

import java.util.Date;
import java.util.List;


public class Owner extends Person {
	
	public Owner(String name, String address, Date dob) {
		super(name, address, dob);
	}	
	
	
	public List<Pet> displayPets() {
		return Pet.getPets();
	}
	
	

}
