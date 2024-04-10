package com.immunet.immunet.controllers;
import com.immunet.immunet.dto.BillingItemDTO;
import com.immunet.immunet.entity.Pet;
import com.immunet.immunet.repository.PetRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetsController {
	@Autowired
	PetRepository petRepository;

	@GetMapping("/pets")
	public List<Pet> getAllPets() {
		return petRepository.findAll();
	}

	@GetMapping("/bills")
	public List<BillingItemDTO> getBill() {
		List<BillingItemDTO> bill = new ArrayList<BillingItemDTO>();
		BillingItemDTO billItem = new BillingItemDTO("Vaccine1", (float) 200.0, "Service");
		bill.add(billItem);
		return bill;
	}
}
