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
import com.immunet.immunet.exception.NotFound;
import com.immunet.immunet.exception.Unauthorized;
import com.immunet.immunet.model.Doctor;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImmunizationReportsController {
	
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
	
	

	@GetMapping("/doctors/{doctorId}/pets/{petId}/immunizations")
	public ImmunizationReportDTO getPetImmunizations(@PathVariable Integer doctorId, @PathVariable Integer petId) throws Unauthorized, NotFound, BadRequest {
		// Validate access
		Optional<DoctorEntity> doctor = doctorRepository.findById(doctorId);
		if(doctor.isEmpty()) {
			throw new Unauthorized("Unauthorized access !");
		}
		Optional<PetEntity> pet = petRepository.findById(petId);
		if(pet.isEmpty()) {
			throw new NotFound("Pet not found with given ID");
		}
		Pet reportFor= petFactory.getPet(pet.get());
		ImmunizationReport report = immunizationReportFactory.getReport(reportFor);
		return getImmunizationReportResponse(reportFor, report);
	}
	
	@PostMapping("/doctors/{doctorId}/pets/{petId}/schedules/{scheduleId}")
	public ImmunizationReportDTO administerVaccination(@PathVariable Integer doctorId, @PathVariable Integer petId, @PathVariable Integer scheduleId) throws BadRequest, Unauthorized, Conflict, NotFound {
		// Validate access
		Optional<DoctorEntity> doctor = doctorRepository.findById(doctorId);
		if(doctor.isEmpty()) {
			throw new Unauthorized("Unauthorized access !");
		}
		Optional<PetEntity> pet = petRepository.findById(petId);
		if(pet.isEmpty()) {
			throw new NotFound("Pet not found with given ID");
		}
		Pet reportFor= petFactory.getPet(pet.get());

		Doctor admin = new Doctor();
		admin.load(doctor.get());

		ImmunizationReport report = immunizationReportFactory.getReport(reportFor);
		report.completeShot(scheduleId, admin);
		report.save(doctor.get().getUserDetails().getId());
		return getImmunizationReportResponse(reportFor, report);
	}
	
	/*
	@GetMapping("/bills")
	public List<BillingItemDTO> getBill() {
		List<BillingItemDTO> bill = new ArrayList<BillingItemDTO>();
		BillingItemDTO billItem = new BillingItemDTO("Vaccine1", (float) 200.0, "Service");
		bill.add(billItem);
		return bill;
	}*/
	
	
	private ImmunizationReportDTO getImmunizationReportResponse(Pet p, ImmunizationReport report) {
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
		return reportResponse;
	}

}
