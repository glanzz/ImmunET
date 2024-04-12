package com.immunet.immunet.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.immunet.immunet.entity.ScheduleEntity;

import jakarta.persistence.EntityManager;

@Repository
public class ScheduleDAO implements EntityDAO<ScheduleEntity> {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<ScheduleEntity> get() {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public ScheduleEntity get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(ScheduleEntity schedule) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.persist(schedule);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}
}
