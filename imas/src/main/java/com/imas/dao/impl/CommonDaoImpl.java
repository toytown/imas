package com.imas.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.imas.dao.interfaces.CommonDao;
import com.imas.model.Category;
import com.imas.model.CategoryType;
import com.imas.model.HeatingType;
import com.imas.model.PostalCode;

@Repository("commonDao")
public class CommonDaoImpl implements CommonDao {
	
    private EntityManager entityManager;

    @Autowired
    protected SimpleJdbcTemplate jdbcTemplate;
    
    @PersistenceContext(name = "imasPU")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
	public List<CategoryType> getTypes() {
        TypedQuery<CategoryType> q = entityManager.createQuery("select t from CategoryType t", CategoryType.class);
        return q.getResultList();		
	}

	public List<HeatingType> getHeatingTypes() {
        TypedQuery<HeatingType> q = entityManager.createQuery("from HeatingType as h", HeatingType.class);
        return q.getResultList();   		
	}
	
	public List<Category> getCategories() {
        TypedQuery<Category> q = entityManager.createQuery("from Category as c", Category.class);
        return q.getResultList();  		
	}

    @Override
    public List<String> findCityByZipCode(String zipCode) {
        TypedQuery<PostalCode> q = entityManager.createQuery("select p from PostalCode p where p.zipCode=:zipCode", PostalCode.class);
        q.setParameter("zipCode", zipCode);
        List<String> cities = new ArrayList<String>();
        for (PostalCode postalCode : q.getResultList()) {            
            cities.add(postalCode.getCity());
        }
        return cities; 
    }	
}
