package com.immunet.immunet.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.immunet.immunet.dto.CreateDoctorDTO;
import com.immunet.immunet.entity.DoctorEntity;
import com.immunet.immunet.exception.BadRequest;
import com.immunet.immunet.model.Doctor;
import com.immunet.immunet.model.User;
import com.immunet.immunet.repository.DoctorRepository;


@RestController
public class DoctorsController {
	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	Doctor doctor;
	
	@CrossOrigin
	@PostMapping("/doctors")
	public Doctor createDoctor(@Validated @RequestBody CreateDoctorDTO doctorData) throws BadRequest {
		if (User.comparePassword(doctorData.getPassword(), doctorData.getRePassword()) == false) {
			throw new BadRequest("Passwords do not match !");
		}
		
		doctor.setName(doctorData.getName());
		doctor.setClinicAddress(doctorData.getAddress());
		doctor.setUsername(doctorData.getContact());
		doctor.setPassword(doctorData.getPassword());
		doctor.setServiceCost(doctorData.getServiceCost());
		doctor.save();
		return doctor;
	}
	
	@CrossOrigin
	@GetMapping("/doctors")
	public List<Doctor> getAllDoctors() {
		List<DoctorEntity> doctors = doctorRepository.findAll();
		List<Doctor> allDoctors = new ArrayList<Doctor>();
		for (DoctorEntity doctorEntity: doctors) {
			Doctor doctor = new Doctor();
			doctor.load(doctorEntity);
			allDoctors.add(doctor);
		}
		return allDoctors;
	}

}
