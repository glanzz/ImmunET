package com.immunet.immunet.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.immunet.immunet.dto.CreateVaccineDTO;
import com.immunet.immunet.entity.DoctorEntity;
import com.immunet.immunet.exception.BadRequest;
import com.immunet.immunet.exception.Conflict;
import com.immunet.immunet.exception.Unauthorized;
import com.immunet.immunet.model.Species;
import com.immunet.immunet.model.Vaccine;
import com.immunet.immunet.model.VaccineFactory;

@RestController
public class VaccinesController {
	@Autowired
	JpaRepository<DoctorEntity, Integer> doctorRepository;
	
	@Autowired
	VaccineFactory vaccineFactory;
	
	
	
	@PostMapping("/doctors/{doctorId}/vaccines")
	public Vaccine createVaccines(@PathVariable Integer doctorId, @Validated @RequestBody CreateVaccineDTO vaccineData) throws BadRequest, Unauthorized, Conflict {
		Optional<DoctorEntity> doctor = doctorRepository.findById(doctorId);
		if(doctor.isEmpty()) {
			throw new Unauthorized("Unauthorized access !");
		}
		Species vaccineSpecies;

		try {
			vaccineSpecies = Species.valueOf(vaccineData.getSpecies());
		} catch(IllegalArgumentException e) {
			throw new BadRequest("Invalid species values given !");
		}
		
		if (vaccineData.getCost() < 0) {
			throw new BadRequest("Negative cost values not allowed!");
		}
		
		Vaccine vaccine = vaccineFactory.getVaccine(
				vaccineData.getName(),
				vaccineSpecies,
				vaccineData.getFrequency(),
				vaccineData.getIntervals(),
				vaccineData.getOffset(),
				doctor.get().getId()
		);
		
		if (vaccine.isPersisted()) {
			throw new Conflict("Vaccine already exists with ID:"+ vaccine.getId());
		}
		vaccine.setSpecies(vaccineSpecies);
		vaccine.setDefaultVaccine(vaccineData.isMandatory());
		vaccine.setCost(vaccineData.getCost());
		vaccine.save(doctor.get());
		
		return vaccine;
	}

}
