package com.tripadvisor.capstone.business.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * To test the send method of email 
 * 
 * TODO : Add the negative test cases 
 * 
 * @author rachana
 * @since  Aug 10, 2014
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmailManagerIntegrationTest {
    @Autowired
    EmailManager emailManager;
    @Test
    public void send() {
        emailManager.send("temp@gmail.com", "test subject", "test body");
    }
}
