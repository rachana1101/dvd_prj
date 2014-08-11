package com.tripadvisor.capstone.business.dao;

import java.util.UUID;

import com.tripadvisor.capstone.business.entity.Movie;

/**
 * @author rachana
 * @since  Aug 10, 2014
 * 
 */
public interface MovieDao {
    /**
     * To find the movie by id
     * @param id {@link UUID}
     * @return {@link Movie}
     */
    public Movie findById(UUID id);
    /**
     * To insert the movie
     * @param movie
     */
    public void insert(Movie movie);
}
