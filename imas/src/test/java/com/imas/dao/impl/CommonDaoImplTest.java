package com.imas.dao.impl;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.imas.test.utils.IMASTestCase;

public class CommonDaoImplTest extends IMASTestCase {

    @Test
    public void testGetTypes() {
        assertTrue(commonDao.getTypes().size() > 2);
    }

    @Test
    public void testGetHeatingTypes() {
        assertTrue(commonDao.getHeatingTypes().size() >= 3);
    }

    @Test
    public void testGetCategories() {
        assertTrue(commonDao.getHeatingTypes().size() >= 3);
    }

    @Test
    public void testFindCityByZipCode() {
        List<String> cityStr = commonDao.findCityByZipCode("81669");
        assertNotNull(cityStr);
        assertTrue(cityStr.get(0).equalsIgnoreCase("Munich") || cityStr.get(0).equalsIgnoreCase("München"));
    }    
}
