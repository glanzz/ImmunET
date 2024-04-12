package com.immunet.immunet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.immunet.immunet.entity.ScheduleEntity;
import com.immunet.immunet.entity.VaccineEntity;


public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Integer>{
	List<ScheduleEntity> findAllByPetId(Integer petId);

}