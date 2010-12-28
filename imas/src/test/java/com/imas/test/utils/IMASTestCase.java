package com.imas.test.utils;

import java.util.Date;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.imas.common.AppConfig;
import com.imas.dao.interfaces.CommonDao;
import com.imas.dao.interfaces.UserDao;
import com.imas.model.ContactDetails;
import com.imas.model.User;

@TransactionConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:imasAppContext.xml" })
public class IMASTestCase {

    @Resource
    protected CommonDao commonDao;

    @Resource
    protected UserDao userDao;
    
    @Resource
    protected AppConfig appConfig;
    
    @Ignore
    @Test
    public void test() {
        Assert.assertEquals(true, true);
    }
    
    protected User createTestUser(String username) {
        ContactDetails userContactDetails = new ContactDetails();
        userContactDetails.setStreet("test street");
        userContactDetails.setHouseNumber("4");
        userContactDetails.setEmail("ptuladhar@gmx.net");
        
        User user= new User();
        user.setUserName(username);
        user.setPassword(username);
        user.setInsertTs(new Date());
        user.getContactDetails().add(userContactDetails);
        return userDao.save(user);
    }
}
