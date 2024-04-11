package com.immunet.immunet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immunet.immunet.dao.DoctorDAO;
import com.immunet.immunet.dao.UserDAO;
import com.immunet.immunet.dao.VaccineDAO;
import com.immunet.immunet.entity.DoctorEntity;
import com.immunet.immunet.entity.VaccineEntity;
import com.immunet.immunet.model.Doctor;
import com.immunet.immunet.model.Vaccine;

import jakarta.transaction.Transactional;

@Service
public class VaccineService {
	
	@Autowired
	private VaccineDAO vaccineDAO;
	
	@Transactional
	public int save(Vaccine vaccine, DoctorEntity doctor) {
		VaccineEntity vaccineEntity = new VaccineEntity(
				vaccine.getName(),
				vaccine.getFrequency(),
				vaccine.getOffset(),
				vaccine.isDefaultVaccine(),
				vaccine.getIntervalsString(),
				vaccine.getCost()
		);
		vaccineEntity.setSpecies(vaccine.getSpecies().name());
		vaccineEntity.setDoctor(doctor);
		vaccineDAO.save(vaccineEntity);
		return vaccineEntity.getId();
	}

}
