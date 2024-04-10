package com.immunet.immunet.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.immunet.immunet.entity.UserEntity;

import jakarta.persistence.EntityManager;

@Repository
public class UserDAO implements EntityDAO<UserEntity> {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<UserEntity> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntity get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(UserEntity user) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.persist(user);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
}
