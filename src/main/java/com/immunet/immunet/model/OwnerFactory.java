package com.immunet.immunet.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.immunet.immunet.entity.OwnerEntity;
import com.immunet.immunet.service.OwnerService;

@Component
public class OwnerFactory {
	
	@Autowired
	OwnerService service;
	
	public Owner getOwner(String name, String address) {
		OwnerEntity existingOwner = service.getExistingOwner(name, address);
		if (existingOwner != null) {
			Owner owner = new Owner(service);
			owner.load(existingOwner);
			return owner;
		}
		return new Owner(service, name, address);
	}
	
	public Owner getOwner(OwnerEntity entity) {
		Owner owner = new Owner(service);
		owner.load(entity);
		return owner;
	}

}
