package com.imas.dao.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import com.imas.dao.interfaces.GenericDao;

@Repository("genericDao")
public class GenericDaoImpl<E, PK extends Serializable> implements GenericDao<E, PK> {

    protected static final Logger logger = Logger.getLogger(GenericDaoImpl.class);
    
    protected EntityManager entityManager;
    
    @Autowired
    @Qualifier("jdbcTemplate")
    protected SimpleJdbcTemplate jdbcTemplate;
    
    @Autowired
    protected JpaTemplate jpaTemplate;

    private Class<E> entityClass;

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
