package com.imas.dao.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.imas.dao.interfaces.UserDao;
import com.imas.model.ContactDetails;
import com.imas.model.User;
import com.imas.test.utils.IMASTestCase;

public class UserDaoImplTest extends IMASTestCase {

	@Resource
	private UserDao userDao;
	
	@Test
	@Transactional
	@Rollback(false)
	public void testSave() {
	
		ContactDetails userContactDetails = new ContactDetails();
		userContactDetails.setStreet("Schlesierstr");
		userContactDetails.setHouseNumber("4");
		userContactDetails.setEmail("ptuladhar@gmx.net");
		
		User user= new User();
		user.setUserName("test-username");
		user.setPassword("password");
		user.setInsertTs(new Date());
		
		user.getContactDetails().add(userContactDetails);
		
		User savedUser = userDao.save(user);
		
		assertTrue(savedUser.getContactDetails().get(0).getStreet().equals("Schlesierstr"));
		assertTrue(savedUser.getContactDetails().get(0).getHouseNumber().equals("4"));
		assertTrue(savedUser.getContactDetails().get(0).getEmail().equals("ptuladhar@gmx.net"));
	}

	
	@Test
	@Transactional
	@Rollback(false)
	public void testUserValidation() {
	
        ContactDetails userContactDetails = new ContactDetails();
        userContactDetails.setStreet("Schlesierstr");
        userContactDetails.setHouseNumber("4");
        userContactDetails.setEmail("ptuladhar@gmx.net");
        
        User user= new User();
        user.setUserName("user-1");
        user.setPassword("password-1");
        user.setInsertTs(new Date());
        
        user.getContactDetails().add(userContactDetails);
        
        User savedUser = userDao.save(user);
        
        assertTrue(savedUser.getContactDetails().get(0).getStreet().equals("Schlesierstr"));
        assertTrue(savedUser.getContactDetails().get(0).getHouseNumber().equals("4"));
        assertTrue(savedUser.getContactDetails().get(0).getEmail().equals("ptuladhar@gmx.net"));
        
        assertNotNull(userDao.findUser("user-1", "password-1"));
        
	}	
}
