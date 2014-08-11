package com.tripadvisor.capstone.business.dao.impl;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.tripadvisor.capstone.business.entity.DVD;

/**
 * @author rachana
 * @since  Aug 9, 2014
 * 
 */
@Component
public class BorrowedDaoImpl {
    public static final String BORROWED_FIELDS = "`dvd_id`, `user_id`, `due_date`, `borrowed_on`";
    
    public static final String TABLE = "borrowed";
    
    @Autowired
    private DataSource dataSource;

    /**
     * @param dataSource
     *            the dataSource to set
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public void insert(DVD dvd) {
        assertThat(dvd).isNotNull();
        assertThat(dvd.getMovieId()).isNotNull();
        String sql = "INSERT INTO "+TABLE+" "+BORROWED_FIELDS+" VALUES (?, ?, ?, ?, ?) ";
        UUID dvdId = UUID.randomUUID();
        new JdbcTemplate(dataSource)
                    .update(sql, new Object[] {
                            dvdId.toString(),
                            dvd.getPrice(),
                            dvd.getUserId(),
                            dvd.getStatus().getValue(),
                            dvd.getDescripton()});
    }
}
