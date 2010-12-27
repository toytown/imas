package com.imas.dao.interfaces;

import com.imas.model.User;

public interface UserDao extends GenericDao<User, Long>{

	public User findUser(String userName, String password);

}
