package com.imas.dao.impl;


import static org.junit.Assert.*;

import org.junit.Test;

import com.imas.test.utils.IMASTestCase;

public class CommonDaoImplTest extends IMASTestCase {

    @Test
    public void testGetTypes() {
        assertTrue(commonDao.getTypes().size() > 2);
    }

    @Test
    public void testGetHeatingTypes() {
        assertTrue(commonDao.getHeatingTypes().size() >= 4);
    }

    @Test
    public void testGetCategories() {
        
    }

}
