package com.immunet.immunet.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.immunet.immunet.entity.Pet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class PetDAO implements EntityDAO<Pet> {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Pet> get() {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		Query query  = currentSession.createQuery("from pets", Pet.class);
		List<Pet> pets = query.getResultList();
		return pets;
	}

	@Override
	public Pet get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Pet pet) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.persist(pet);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
}
