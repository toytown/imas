package com.imas.services.interfaces;

import com.imas.model.User;
import com.imas.valueobjects.ContactRequest;

public interface AccountManagementService {

	public User findUser(String userName, String password);

	public void save(User aUser);
	
	public User update(User aUser);
	
	public boolean sendActivationEmail(User customer) ;
	
	public boolean sendPasswordEmail(String user, String oldPassword) ;
	
	public User findUserById(Long userId);
	
	public void sendContactMessage(User customer, ContactRequest contactRequest);
}