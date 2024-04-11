package com.immunet.immunet.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.immunet.immunet.entity.EntitySpecies;
import com.immunet.immunet.entity.VaccineEntity;
import com.immunet.immunet.exception.BadRequest;
import com.immunet.immunet.repository.VaccineRepository;
import com.immunet.immunet.service.VaccineService;

@Component
public class VaccineFactory {
	@Autowired
	VaccineService vaccineService;
	
	@Autowired
	VaccineRepository vaccineRepository;
	
	public VaccineFactory() {
		
	}
	
	public Vaccine getVaccine(String name, Species species, int frequency, String intervalsCSV, int offset, int doctorId) throws BadRequest {
		VaccineEntity vaccineEntity = vaccineRepository.findDistinctByNameAndSpeciesAndDoctorId(name, EntitySpecies.valueOf(species.name()), doctorId);
		if (vaccineEntity != null) {
			// Load existing vacccine
			Vaccine existingVaccine = new Vaccine();
			existingVaccine.load(vaccineEntity);
			return existingVaccine;
			
		} else {
			return new Vaccine(vaccineService, name, species, frequency,  intervalsCSV, offset) ;
		}
		
	}
		
		

}
