package com.immunet.immunet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immunet.immunet.dao.DoctorDAO;
import com.immunet.immunet.dao.UserDAO;
import com.immunet.immunet.entity.DoctorEntity;
import com.immunet.immunet.entity.UserEntity;
import com.immunet.immunet.model.Doctor;


import jakarta.transaction.Transactional;

@Service
public class DoctorService {
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private DoctorDAO doctorDAO;
	
	@Transactional
	public int save(Doctor doctor) {
		UserEntity userEntity = new UserEntity(doctor.getName(), doctor.getUsername(), doctor.getPassword(), doctor.getBillingAddress());
		DoctorEntity doctorEntity = new DoctorEntity(doctor.getServiceCost(), doctor.getClinicAddress());
		doctorEntity.setUserDetails(userEntity);
		userDAO.save(userEntity);
		doctorDAO.save(doctorEntity);
		return doctorEntity.getId();
	}

}
