package com.immunet.immunet.controllers;
import com.immunet.immunet.dto.BillingItemDTO;
import com.immunet.immunet.entity.PetEntity;
import com.immunet.immunet.repository.PetRepository;
import com.immunet.immunet.service.PetService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetsController {
	@Autowired
	PetRepository petRepository;
	
	@Autowired
	PetService petService;

	@GetMapping("/pets")
	public List<PetEntity> getAllPets() {
		return petRepository.findAll();
	}
	
	@PostMapping("/pets")
	public PetEntity save(@RequestBody PetEntity pet) {
		petService.save(pet);
		return pet;
	}
	

	@GetMapping("/bills")
	public List<BillingItemDTO> getBill() {
		List<BillingItemDTO> bill = new ArrayList<BillingItemDTO>();
		BillingItemDTO billItem = new BillingItemDTO("Vaccine1", (float) 200.0, "Service");
		bill.add(billItem);
		return bill;
	}
}
