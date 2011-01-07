package com.imas.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;

import com.imas.dao.interfaces.GenericDao;

public abstract class GenericDaoImpl<E, PK extends Serializable> implements GenericDao<E, PK> {

    protected static final Logger logger = Logger.getLogger(GenericDaoImpl.class);
    protected EntityManager entityManager;
    private Class<E> entityClass;
    
    @Autowired
    protected JpaTemplate jpaTemplate;
    
    @SuppressWarnings("unchecked")
    public GenericDaoImpl() {
        super();
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
    }


    @PersistenceContext(name = "imasPU")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void delete(E entity) {
        entityManager.remove(entity);
    }

    public E findById(PK id) {
        return entityManager.find(entityClass, id);
    }

    public E save(E object) {
        entityManager.persist(object);
        return object;
    }

    public E update(E entity) {
        return entityManager.merge(entity);
    }
}
