package com.immunet.immunet.dao;

import java.util.List;

public interface EntityDAO<T> {
	List<T> get();
	T get(Integer id);
	void save(T entity);
	void delete(Integer id);

}
