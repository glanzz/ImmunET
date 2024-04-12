package com.immunet.immunet.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.immunet.immunet.entity.PetEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class PetDAO implements EntityDAO<PetEntity> {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<PetEntity> get() {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		Query query  = currentSession.createQuery("from pets", PetEntity.class);
		List<PetEntity> pets = query.getResultList();
		return pets;
	}

	@Override
	public PetEntity get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(PetEntity pet) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.persist(pet);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
}
