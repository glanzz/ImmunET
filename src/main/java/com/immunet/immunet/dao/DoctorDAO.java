package com.immunet.immunet.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.immunet.immunet.entity.DoctorEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class DoctorDAO implements EntityDAO<DoctorEntity> {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<DoctorEntity> get() {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		Query query  = currentSession.createQuery("from doctors", DoctorEntity.class);
		List<DoctorEntity> doctors = query.getResultList();
		return doctors;
	}

	@Override
	public DoctorEntity get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(DoctorEntity doctor) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.persist(doctor);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
}
