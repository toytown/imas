package com.imas.test.utils;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.imas.dao.interfaces.CommonDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/imasAppContextTest.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class IMASTestCase {

    @Autowired
    @Qualifier("commonDao")
    protected CommonDao commonDao;
    
    @Autowired
    @Qualifier("jdbcTemplate")
    protected SimpleJdbcTemplate jdbcTemplate;
    
}
