package com.immunet.immunet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immunet.immunet.dao.PetDAO;
import com.immunet.immunet.entity.Pet;

import jakarta.transaction.Transactional;

@Service
public class PetService {
	
	@Autowired
	private PetDAO petDAO;
	
	@Transactional
	public void save(Pet p) {
		petDAO.save(p);
	}

}
