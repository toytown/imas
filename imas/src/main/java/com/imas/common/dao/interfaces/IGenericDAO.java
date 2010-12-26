package com.imas.common.dao.interfaces;

import java.io.Serializable;
import java.util.List;

public interface IGenericDAO<T, PK extends Serializable>{

	public void save(T row);
	public T update(T row);
	public void delete(T row);
	
	public T findById(PK id);
	public List<T> findAll();
	
	
}
