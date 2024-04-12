package com.immunet.immunet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immunet.immunet.dao.DoctorDAO;
import com.immunet.immunet.dao.OwnerDAO;
import com.immunet.immunet.dao.UserDAO;
import com.immunet.immunet.entity.OwnerEntity;
import com.immunet.immunet.entity.UserEntity;
import com.immunet.immunet.model.Doctor;
import com.immunet.immunet.model.Owner;
import com.immunet.immunet.repository.OwnerRepository;

import jakarta.transaction.Transactional;

@Service
public class OwnerService {
	
	@Autowired
	private OwnerDAO ownerDAO;
	
	@Autowired
	private OwnerRepository ownerRepository;
	
	@Transactional
	public int save(Owner owner, Integer userId) {
		OwnerEntity ownerEntity = new OwnerEntity(
				owner.getName(),
				owner.getAddress()
		);
		ownerEntity.setCreatedBy(userId);
		ownerDAO.save(ownerEntity);
		return ownerEntity.getId();
	}
	
	public OwnerEntity getExistingOwner(String name, String address) {
		return ownerRepository.findDistinctByNameAndAddress(name, address);
	}

}