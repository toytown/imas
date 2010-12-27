package com.imas.dao.interfaces;

import java.io.Serializable;

public interface GenericDao<T, PK extends Serializable>{

	public T save(T row);
	public T update(T row);
	public void delete(T row);
	public T findById(PK id);
	
}
