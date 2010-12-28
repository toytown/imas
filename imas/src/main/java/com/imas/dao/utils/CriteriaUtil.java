package com.imas.dao.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.imas.valueobjects.SearchFilter;

public class CriteriaUtil {
    
    private CriteriaBuilder cb;
    private List<Predicate> predicateList;
    private Map<String, Object> parameterMap;
    private int i = 0;
    
    public CriteriaUtil(CriteriaBuilder cb) {
        predicateList = new ArrayList<Predicate>();
        this.cb = cb;
        parameterMap = new HashMap<String, Object>();
    }
    
    public <T> CriteriaUtil equal(T property, String propertyName, Root<?> root) {
        if (property != null) {
            String paramName = generateParamName(propertyName);
            predicateList.add(cb.equal(root.get(propertyName), cb.parameter(property.getClass(), paramName)));
            parameterMap.put(paramName, property);
        }
        return this;
    }
    
    public <T> CriteriaUtil equal(T property, String propertyName, Join<?, ?> join) {
        if (property != null) {
            String paramName = generateParamName(propertyName);
            predicateList.add(cb.equal(join.get(propertyName), cb.parameter(property.getClass(), paramName)));
            parameterMap.put(paramName, property);
        }
        return this;
    }

    public <T> CriteriaUtil in(String propertyName, Collection<?> values, Root<?> root) {
        StringBuilder buffer = new StringBuilder();
        
        boolean first = false;
        
        for (Object val : values) {        
            
            if (val instanceof Integer || val instanceof Double || val instanceof Float) {
                if (first) {
                    buffer.append(", ");                
                } else {
                    buffer.append("'");
                    buffer.append(val);
                    buffer.append("'");
                    first = true;
                }
            } else {
                if (first) {
                    buffer.append(", ");                
                } else {                
                    buffer.append(val);
                    first = true;
                }                
            }
        }
            
        if (propertyName != null && !values.isEmpty()) {
            predicateList.add(root.get(propertyName).in(values));
            
            //predicateList.add(cb.in(root.get(propertyName)).value(AggregateTypeCode.IC_TYPE));
        }
        return this;
    } 
    

    
    /**
     * 
     * @param property
     * @param propertyName
     * @param root
     * @param appendWildcards if true, the wildcards (%) will be added at the beginning and the end of search parameter
     * @param caseInsensitive if true, the search will be case insensitive, i.e. the given search string will be converted 
     *          to uppercase and sql upper function will be used
     * @return this
     */
    public CriteriaUtil like(String property, String propertyName, Root<?> root, boolean appendWildcards, boolean caseInsensitive) {
        if (property != null) {
            String paramName = generateParamName(propertyName);
            if (caseInsensitive) {
                predicateList.add(cb.like(cb.upper(root.<String>get(propertyName)), cb.parameter(String.class, paramName)));
                property = property.toUpperCase();
                parameterMap.put(paramName, appendWildcards ? "%" + property + "%" : property);
            } else {
                predicateList.add(cb.like(root.<String>get(propertyName), cb.parameter(String.class, paramName)));
                parameterMap.put(paramName, appendWildcards ? "%" + property + "%" : property);
            }
        }
        return this;
    }
    
    public <T> CriteriaUtil notEqual(T property, String propertyName, Root<?> root) {
        if (property != null) {
            String paramName = generateParamName(propertyName);
            predicateList.add(cb.notEqual(root.get(propertyName), cb.parameter(property.getClass(), paramName)));
            parameterMap.put(paramName, property);
        }
        return this;
    }
    
    @SuppressWarnings("unchecked")
    public <T extends Comparable<? super T>> CriteriaUtil greaterThan(T property, String propertyName, Root<?> root) {
        if (property != null) {
            String paramName = generateParamName(propertyName);
            Expression<T> expression = root.get(propertyName);
            predicateList.add(cb.greaterThan(expression, cb.parameter(property.getClass(), paramName)));
            parameterMap.put(paramName, property);
        }
        return this;
    }
    
    @SuppressWarnings("unchecked")
    public <T extends Comparable<? super T>> CriteriaUtil lessThan(T property, String propertyName, Root<?> root) {
        if (property != null) {
            String paramName = generateParamName(propertyName);
            Expression<T> expression = root.get(propertyName);
            predicateList.add(cb.lessThan(expression, cb.parameter(property.getClass(), paramName)));
            parameterMap.put(paramName, property);
        }
        return this;
    }
    
    @SuppressWarnings("unchecked")
    public <T extends Comparable<? super T>> CriteriaUtil greaterThanOrEqualTo(T property, String propertyName, Root<?> root) {
        if (property != null) {
            String paramName = generateParamName(propertyName);
            Expression<T> expression = root.get(propertyName);
            predicateList.add(cb.greaterThanOrEqualTo(expression, cb.parameter(property.getClass(), paramName)));
            parameterMap.put(paramName, property);
        }
        return this;
    }
    
    @SuppressWarnings("unchecked")
    public <T extends Comparable<? super T>> CriteriaUtil lessThanOrEqualTo(T property, String propertyName, Root<?> root) {
        if (property != null) {
            String paramName = generateParamName(propertyName);
            Expression<T> expression = root.get(propertyName);
            predicateList.add(cb.lessThanOrEqualTo(expression, cb.parameter(property.getClass(), paramName)));
            parameterMap.put(paramName, property);
        }
        return this;
    }
    
    @SuppressWarnings("unchecked")
    public <T extends Comparable<? super T>> CriteriaUtil between(T property1, String propertyName, T property2, Root<?> root) {
        if (property1 != null && property2 != null) {
            String param1Name = generateParamName(propertyName);
            String param2Name = generateParamName(propertyName);
            Expression<T> expression = root.get(propertyName);
            predicateList.add(cb.between(expression, cb.parameter(property1.getClass(), param1Name), cb.parameter(property2.getClass(), param2Name)));
            parameterMap.put(param1Name, property1);
            parameterMap.put(param2Name, property2);
        }
        return this;
    }
    
    /**
     * creates a group of inner OR clauses, i.e. (property=propertyValue1 OR property=propertyValue2 OR property=propertyValue3)
     */
    public <T> CriteriaUtil orGroup(T[] propertyValues, String propertyName, Root<?> root) {
        if (propertyValues != null && propertyValues.length != 0) {
            Expression<T> expression = root.get(propertyName);
            Predicate[] predicates = new Predicate[propertyValues.length];
            for (int i = 0; i < propertyValues.length; i++) {
                T propertyValue = propertyValues[i];
                String paramName = generateParamName(propertyName);
                predicates[i] = cb.equal(expression, cb.parameter(propertyValue.getClass(), paramName));
                parameterMap.put(paramName, propertyValue);
            }
            predicateList.add(cb.or(predicates));
        }
        return this;
    }
    
    /**
     * creates a group of inner OR clauses, i.e. (property=propertyValue1 OR property=propertyValue2 OR property=propertyValue3)
     */
    public <T> CriteriaUtil orGroup(Collection<T> propertyValues, String propertyName, Root<?> root) {
        if (propertyValues != null && !propertyValues.isEmpty()) {
            orGroup(propertyValues.toArray(), propertyName, root);
        }
        
        return this;
    }
    
    public CriteriaUtil addPredicate(Predicate p) {
        predicateList.add(p);
        return this;
    }
    
    public <T> TypedQuery<T> createQuery(EntityManager entityManager, CriteriaQuery<T> criteriaQuery) {
        if (predicateList.isEmpty()) {
            return entityManager.createQuery(criteriaQuery);
        }
        
        TypedQuery<T> q = entityManager.createQuery(criteriaQuery.where(cb.and(predicateList.toArray(new Predicate[0]))));
        
        for (Map.Entry<String, Object> parameterEntry : parameterMap.entrySet()) {
            q.setParameter(parameterEntry.getKey(), parameterEntry.getValue());
        }
        
        return q;
    }
    
    public static <T> void setPagination(TypedQuery<T> query, SearchFilter searchFilter) {
        if (searchFilter.isPaginationSet()) {
            query.setFirstResult((searchFilter.getPageNumber() * searchFilter.getPageSize()) - searchFilter.getPageSize());
            query.setMaxResults(searchFilter.getPageSize());
        }
    }
    
    private String generateParamName(String propertyName) {
        return propertyName + (i++);
    }
}
