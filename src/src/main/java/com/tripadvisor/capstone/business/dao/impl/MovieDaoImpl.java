package com.tripadvisor.capstone.business.dao.impl;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.tripadvisor.capstone.business.dao.Constants;
import com.tripadvisor.capstone.business.dao.MovieDao;
import com.tripadvisor.capstone.business.entity.Movie;

/**
 * @author rachana
 * @since Aug 9, 2014
 * 
 */
@Component
public class MovieDaoImpl implements MovieDao {

    @Autowired
    private DataSource dataSource;

    /**
     * @param dataSource
     *            the dataSource to set
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tripadvisor.capstone.business.dao.MovieDao#findById(java.util.UUID)
     */
    public Movie findById(UUID id) {
        assertThat(dataSource).isNotNull();
        String sql = "SELECT " + Constants.Movie.FIELDS + " FROM "
                + Constants.Movie.TABLE + " WHERE id = ? ";
        return (Movie) new JdbcTemplate(dataSource).queryForObject(sql,
                new Object[] { id.toString() }, new MovieRowMapper());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tripadvisor.capstone.business.dao.MovieDao#insert(com.tripadvisor
     * .capstone.business.entity.Movie)
     */
    public void insert(Movie movie) {
        assertThat(movie).isNotNull();
        String sql = "INSERT INTO " + Constants.Movie.TABLE + " ( "
                + Constants.Movie.FIELDS + " ) VALUES (?, ?, ?, ?, ?) ";
        new JdbcTemplate(dataSource).update(sql, new Object[] {
                UUID.randomUUID().toString(), movie.getName(),
                movie.getGenre(), movie.getStudio(), movie.getYear() });
    }

}
