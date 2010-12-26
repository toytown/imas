package com.imas.common.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.jpa.support.JpaDaoSupport;

import com.imas.common.dao.interfaces.IGenericDAO;

public class GenericDAOImpl<E, PK extends Serializable> extends JpaDaoSupport implements IGenericDAO<E, PK> {

	private Class<E> entityClass;
	
	public void delete(E entity) {
		getJpaTemplate().remove(entity);
	}

	public List<E> findAll() {
		return getJpaTemplate().find("select e from " + entityClass.getName() + " e");
	}

	public E findById(PK id) {
		return getJpaTemplate().find(entityClass, id);
	}

	public void save(E entity) {
		getJpaTemplate().persist(entity);

	}

	public E update(E entity) {
		return getJpaTemplate().merge(entity);
	}
	
	public Class<E> getEntityClass() {
		return this.entityClass;
	}

	public void setEntityClass(Class<E> entityClass) {
		this.entityClass = entityClass;
	}


}
