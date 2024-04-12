package com.immunet.immunet.model;

import java.util.Date;
import java.util.List;

import com.immunet.immunet.entity.OwnerEntity;
import com.immunet.immunet.entity.UserEntity;
import com.immunet.immunet.service.OwnerService;


public class Owner extends Person {
	Integer id;
	OwnerService service;
	
	public Integer getId() {
		return this.id;
	}
	
	public Owner(OwnerService service, String name, String address) {
		super(name, address);
		this.service = service;
	}
	
	public Owner(OwnerService service) {
		this.service = service;
	}
	
	
	public void save(Integer userId) {
		if (isPersisted()) {
			return;
		}
		this.id = service.save(this, userId);
	}
	
	
	public boolean isPersisted() {
		return this.id != null;
	}
	
	public void load(OwnerEntity e) {
		this.id = e.getId();
		this.setName(e.getName());
		this.setAddress(e.getAddress());
	}
	
	
	
	
	/*public List<Pet> displayPets() {
		return Pet.getPets();
	}*/
	

}
