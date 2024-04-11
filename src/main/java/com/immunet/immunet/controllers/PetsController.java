package com.immunet.immunet.controllers;
import com.immunet.immunet.dto.BillingItemDTO;
import com.immunet.immunet.dto.CreatePetDTO;
import com.immunet.immunet.dto.CreateVaccineDTO;
import com.immunet.immunet.entity.DoctorEntity;
import com.immunet.immunet.entity.PetEntity;
import com.immunet.immunet.exception.BadRequest;
import com.immunet.immunet.exception.Conflict;
import com.immunet.immunet.exception.Unauthorized;
import com.immunet.immunet.repository.DoctorRepository;
import com.immunet.immunet.repository.PetRepository;
import com.immunet.immunet.service.PetService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetsController {
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	PetRepository petRepository;
	
	@Autowired
	PetService petService;

	@GetMapping("/pets")
	public List<PetEntity> getAllPets() {
		return petRepository.findAll();
	}
	
	@PostMapping("/doctors/{doctorId}/pets")
	public DoctorEntity save(@PathVariable Integer doctorId, @Validated @RequestBody CreatePetDTO petData) throws BadRequest, Unauthorized, Conflict {
		Optional<DoctorEntity> doctor = doctorRepository.findById(doctorId);
		if(doctor.isEmpty()) {
			throw new Unauthorized("Unauthorized access !");
		}
		System.out.println(petData.getOwner());
		return doctor.get();
	}
	

	@GetMapping("/bills")
	public List<BillingItemDTO> getBill() {
		List<BillingItemDTO> bill = new ArrayList<BillingItemDTO>();
		BillingItemDTO billItem = new BillingItemDTO("Vaccine1", (float) 200.0, "Service");
		bill.add(billItem);
		return bill;
	}
}
