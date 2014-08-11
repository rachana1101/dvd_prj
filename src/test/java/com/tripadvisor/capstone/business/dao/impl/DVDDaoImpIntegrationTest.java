package com.tripadvisor.capstone.business.dao.impl;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tripadvisor.capstone.business.entity.DVD;

/**
 * To test all the methods of the dao class 
 * 
 * TODO : Add negative test cases
 * 
 * @author rachana
 * @since Aug 9, 2014
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DVDDaoImpIntegrationTest {
    @Autowired
    DVDDaoImpl dao;
    
    @Before 
    public void setup() {
        assertThat(dao).isNotNull();
    }
    @Test
    public void findById() {
        DVD dvd = dao.findById(UUID.fromString("3f533253-b5ac-48e5-b816-a19852bc9b07"));
        assertThat(dvd).isNotNull();
        assertThat(dvd.getId()).isEqualTo(UUID.fromString("3f533253-b5ac-48e5-b816-a19852bc9b07"));
    }
    
    @Test
    public void search() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("price", new Double(9.99));
        List<DVD> dvds = dao.search(map);
        assertThat(dvds).isNotEmpty();
        for (DVD dvd : dvds) {
            assertThat(dvd.getId()).isNotNull();
            assertThat(dvd.getPrice()).isEqualTo(new Double(9.99));
        }
    }
    
    @Test
    public void findAvailable() {
        List<DVD> dvds = dao.findAvailableDVDs("StarWars");
        assertThat(dvds).isNotEmpty();
        for (DVD dvd : dvds) {
            assertThat(dvd.getId()).isNotNull();
            assertThat(dvd.getTitle()).isEqualTo("StarWars");
        }
    }
    
}
