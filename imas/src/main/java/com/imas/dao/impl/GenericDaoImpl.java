package com.imas.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import com.imas.dao.interfaces.GenericDao;

public class GenericDaoImpl<E, PK extends Serializable> extends JpaDaoSupport implements GenericDao<E, PK> {

    protected static final Logger logger = Logger.getLogger(GenericDaoImpl.class);
    
    protected EntityManager entityManager;
    
    @Autowired
    protected SimpleJdbcTemplate jdbcTemplate;
    
    @Autowired
    protected JpaTemplate jpaTemplate;

    private Class<E> entityClass;

    @SuppressWarnings("unchecked")
    public GenericDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass
                .getActualTypeArguments()[0];
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
