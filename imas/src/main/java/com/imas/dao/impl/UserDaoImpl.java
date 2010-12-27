package com.imas.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.imas.dao.interfaces.UserDao;
import com.imas.model.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

    @Override
    public User findUser(String userName, String password) {
        TypedQuery<User> q = entityManager.createQuery("select u from com.oas.model.Customer u where u.userName=:userName and u.password=:password", User.class);
        q.setParameter("userName", userName);
        q.setParameter("password", password);
        List<User> result = q.getResultList(); 
        return result.get(0);
    }

}
