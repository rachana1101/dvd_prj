package com.tripadvisor.capstone.business.dao.impl;

import static org.fest.assertions.api.Assertions.assertThat;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.tripadvisor.capstone.business.dao.BorrowDao;
import com.tripadvisor.capstone.business.dao.Constants;
import com.tripadvisor.capstone.business.entity.Borrow;

/**
 * @author rachana
 * @since Aug 9, 2014
 * 
 */
@Component
public class BorrowDaoImpl implements BorrowDao {
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
     * com.tripadvisor.capstone.business.dao.BorrowDao#insert(com.tripadvisor
     * .capstone.business.entity.Borrow)
     */
    public void insert(Borrow borrow) {
        assertThat(borrow).isNotNull();
        String sql = "INSERT INTO " + Constants.Borrow.TABLE
                + "(`dvd_id`, `user_id`, `due_date`)  VALUES (?, ?, ?) ";
       new JdbcTemplate(dataSource).update(sql, new Object[] {
                borrow.getDvdId().toString(), borrow.getUserId().toString(),
                borrow.getDueDate() });
    }
}
