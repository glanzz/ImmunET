package com.immunet.immunet.controllers;
import com.immunet.immunet.dto.BillingItemDTO;
import com.immunet.immunet.dto.CreatePetDTO;
import com.immunet.immunet.dto.PetResponseDTO;
import com.immunet.immunet.dto.CreateVaccineDTO;
import com.immunet.immunet.dto.DoctorDTO;
import com.immunet.immunet.dto.ImmunizationReportDTO;
import com.immunet.immunet.entity.DoctorEntity;
import com.immunet.immunet.entity.EntitySpecies;
import com.immunet.immunet.entity.PetEntity;
import com.immunet.immunet.exception.BadRequest;
import com.immunet.immunet.exception.Conflict;
import com.immunet.immunet.exception.Unauthorized;
import com.immunet.immunet.model.ImmunizationReport;
import com.immunet.immunet.model.ImmunizationReportFactory;
import com.immunet.immunet.model.Owner;
import com.immunet.immunet.model.OwnerFactory;
import com.immunet.immunet.model.Pet;
import com.immunet.immunet.model.PetFactory;
import com.immunet.immunet.model.Species;
import com.immunet.immunet.repository.DoctorRepository;
import com.immunet.immunet.repository.PetRepository;
import com.immunet.immunet.service.PetService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	@Autowired
	OwnerFactory ownerFactory;
	
	@Autowired
	PetFactory petFactory;
	
	@Autowired
	ImmunizationReportFactory immunizationReportFactory;
	
	
	@CrossOrigin
	@GetMapping("/pets")
	public List<PetResponseDTO> getAllPets() {
		List<PetEntity> pets = petRepository.findAll();
		List<PetResponseDTO> petsDetails = new ArrayList<PetResponseDTO>();
		pets.forEach(pet -> {
			Pet p = petFactory.getPet(pet);
			Owner o = ownerFactory.getOwner(pet.getOwner());
			ImmunizationReport report;
			try {
				report = immunizationReportFactory.getReport(p);
			} catch (BadRequest e) {
				return;
			}
			petsDetails.add(getPetCreationResponse(o, p, report));
		});
		return petsDetails;
	}
	
	@CrossOrigin
	@PostMapping("/doctors/{doctorId}/pets")
	public PetResponseDTO save(@PathVariable Integer doctorId, @Validated @RequestBody CreatePetDTO petData) throws BadRequest, Unauthorized, Conflict {
		// Validate access
		Optional<DoctorEntity> doctor = doctorRepository.findById(doctorId);
		if(doctor.isEmpty()) {
			throw new Unauthorized("Unauthorized access !");
		}
		
		// Validate data
		Species species = null;
		try {
			species = Species.valueOf(petData.getSpecies());
		} catch(IllegalArgumentException e) {
			throw new BadRequest("Invalid species values given !");
		}
		
		Pet.Gender gender = null;
		try {
			gender = Pet.Gender.valueOf(petData.getGender());
		} catch(IllegalArgumentException e) {
			throw new BadRequest("Invalid gender values given !");
		}
		
		// Create entities
		Owner owner = ownerFactory.getOwner(
				petData.getOwner().getName(),
				petData.getOwner().getAddress()
		);

		Pet pet = petFactory.getPet(
			petData.getName(),
			petData.getDob(),
			gender,
			species,
			doctor.get().getId()
		);
		
		ImmunizationReport report = immunizationReportFactory.getReport(pet);
		
		petService.petCreation(
			doctor.get().getUserDetails().getId(),
			owner,
			pet,
			report
		);

		return getPetCreationResponse(owner, pet, report);
	}
	

	@CrossOrigin
	@GetMapping("/bills")
	public List<BillingItemDTO> getBill() {
		List<BillingItemDTO> bill = new ArrayList<BillingItemDTO>();
		BillingItemDTO billItem = new BillingItemDTO("Vaccine1", (float) 200.0, "Service");
		bill.add(billItem);
		return bill;
	}

	/*
	 * Demonstrates the use of inner class and its creation
	 * 
	 * */
	private PetResponseDTO getPetCreationResponse(Owner o, Pet p, ImmunizationReport report) {
		
		PetResponseDTO response = new PetResponseDTO();
		response.setId(p.getId());
		response.setName(p.getName());
		response.setDob(p.getDob());
		response.setGender(p.getGender().name());
		response.setSpecies(p.getSpecies().name());
		
		PetResponseDTO.CreateOwnerDTO ownerResponse = response.new CreateOwnerDTO(); // Inner class instantiation
		ownerResponse.setId(o.getId());
		ownerResponse.setName(o.getName());
		ownerResponse.setAddress(o.getAddress());
		
		response.setOwner(ownerResponse);
		
		
		ImmunizationReportDTO reportResponse = new ImmunizationReportDTO();
		reportResponse.setName(p.getName());
		reportResponse.setDob(p.getDob());
		
		
		report.getShotRecords().forEach(shotRecord -> {
			ImmunizationReportDTO.ImmunizationRecordDTO reportRecordDTO = reportResponse.new ImmunizationRecordDTO();
			reportRecordDTO.setName(shotRecord.getVaccine().getName());
			reportRecordDTO.setType(shotRecord.getVaccine().requiresMultipleShots() ? "MULTI": "SINGLE");
			reportRecordDTO.setVaccineId(shotRecord.getVaccine().getId());
			shotRecord.getSchedules().forEach(schedule -> {
				ImmunizationReportDTO.ScheduleDTO scheduleResponse = reportResponse.new ScheduleDTO();
				scheduleResponse.setAdministeredDate(schedule.getAdministeredDate());
				scheduleResponse.setId(schedule.getId());
				scheduleResponse.setScheduledDate(schedule.getScheduledDate());
				scheduleResponse.setStatus(schedule.getStatus().name());
				if(schedule.getDoctor() != null) {
					DoctorDTO doctorResponse = new DoctorDTO();
					doctorResponse.setId(schedule.getDoctor().getId());
					doctorResponse.setName(schedule.getDoctor().getName());
					doctorResponse.setClinicAddress(schedule.getDoctor().getClinicAddress());
					scheduleResponse.setDoctor(doctorResponse);
				}
				 
				reportRecordDTO.getSchedules().add(scheduleResponse);
			});
			
			reportResponse.getRecords().add(reportRecordDTO);
		});
		
		response.setReport(reportResponse);

		return response;
		
	}
}
