package com.immunet.immunet.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.immunet.immunet.entity.OwnerEntity;
import com.immunet.immunet.model.Species;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class OwnerDAO implements EntityDAO<OwnerEntity> {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<OwnerEntity> get() {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public OwnerEntity get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(OwnerEntity owner) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.persist(owner);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}
}
