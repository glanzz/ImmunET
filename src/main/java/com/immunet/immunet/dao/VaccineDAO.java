package com.immunet.immunet.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.immunet.immunet.entity.VaccineEntity;
import com.immunet.immunet.model.Species;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class VaccineDAO implements EntityDAO<VaccineEntity> {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<VaccineEntity> get() {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		Query query  = currentSession.createQuery("from vaccines", VaccineEntity.class);
		List<VaccineEntity> vaccines = query.getResultList();
		return vaccines;
	}
	

	@Override
	public VaccineEntity get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(VaccineEntity vaccine) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.persist(vaccine);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}
}
	