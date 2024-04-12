package com.immunet.immunet.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immunet.immunet.dao.DoctorDAO;
import com.immunet.immunet.dao.ScheduleDAO;
import com.immunet.immunet.dao.UserDAO;
import com.immunet.immunet.entity.DoctorEntity;
import com.immunet.immunet.entity.EntitySpecies;
import com.immunet.immunet.entity.ScheduleEntity;
import com.immunet.immunet.entity.UserEntity;
import com.immunet.immunet.entity.VaccineEntity;
import com.immunet.immunet.exception.BadRequest;
import com.immunet.immunet.model.Doctor;
import com.immunet.immunet.model.ImmunizationReport;
import com.immunet.immunet.model.Pet;
import com.immunet.immunet.model.Schedule;
import com.immunet.immunet.model.ShotFactory;
import com.immunet.immunet.model.ShotRecord;
import com.immunet.immunet.model.Vaccine;
import com.immunet.immunet.model.VaccineFactory;
import com.immunet.immunet.repository.DoctorRepository;
import com.immunet.immunet.repository.PetRepository;
import com.immunet.immunet.repository.ScheduleRepository;
import com.immunet.immunet.repository.VaccineRepository;

import jakarta.transaction.Transactional;

@Service
public class ImmunizationReportService {
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private DoctorDAO doctorDAO;
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private VaccineRepository vaccineRepository;
	
	@Autowired
	private VaccineFactory vaccineFactory;
	
	@Autowired
	private ShotFactory shotFactory;
	
	@Autowired
	private ScheduleDAO scheduleDAO;

	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	public List<Vaccine> getDefaultVaccines(Pet p) throws BadRequest {
		List<Vaccine> defaultVaccines = new ArrayList<Vaccine>();
		List<VaccineEntity> vaccines = vaccineRepository.findAllByDoctorIdAndSpeciesAndIsDefault(p.getCreatorID(), EntitySpecies.valueOf(p.getSpecies().name()) , true);
		for(VaccineEntity vaccineEntity: vaccines) {
			defaultVaccines.add(vaccineFactory.getVaccine(vaccineEntity));
		}
		
		return defaultVaccines;
	}
	
	
	public Collection<ShotRecord> getExistingSchedules(Pet p) throws BadRequest {
		List<ScheduleEntity> existingSchedules  = scheduleRepository.findAllByPetId(p.getId());
		Map<Integer, ShotRecord> scheduleMap = new HashMap<Integer, ShotRecord>();
		existingSchedules.stream().forEach(s -> {
			Vaccine v;
			try {
				v = vaccineFactory.getVaccine(s.getVaccine());
			} catch (BadRequest e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			// Add schedules to the v 
			if(scheduleMap.get(v.getId()) == null) {
				ShotRecord record = ShotFactory.getInstance().getShotRecord(v);
				scheduleMap.put(v.getId(), record);
			}
			Schedule schedule = Schedule.load(s);
			scheduleMap.get(v.getId()).addSchedule(schedule);
		});
		return scheduleMap.values();
	}
	
	@Transactional
	public void save(ImmunizationReport report, Integer userId) {
		Pet p = report.getPet();
		List<ShotRecord> shotRecords = report.getShotRecords();
		shotRecords.forEach(record -> {
			record.getSchedules().forEach(schedule -> {
				ScheduleEntity scheduleEntity = schedule.getId() != null ? scheduleRepository.findById(schedule.getId()).get() : null;
				if (scheduleEntity == null) {
					scheduleEntity = new ScheduleEntity();
					scheduleEntity.setCreatedBy(userId);
					scheduleEntity.setPetId(p.getId());
					scheduleEntity.setVaccineId(record.getVaccine().getId());
				}
				scheduleEntity.setScheduleDate(schedule.getScheduledDate());
				scheduleEntity.setTakenDate(schedule.getAdministeredDate());
				scheduleEntity.setDoctorId(schedule.getDoctorId());
				scheduleDAO.save(scheduleEntity);
				schedule.setId(scheduleEntity.getId());
			});
		});
		
	}

}
