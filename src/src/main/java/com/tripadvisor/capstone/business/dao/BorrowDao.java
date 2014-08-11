package com.tripadvisor.capstone.business.dao;

import com.tripadvisor.capstone.business.entity.Borrow;

/**
 * @author rachana
 * @since Aug 10, 2014
 * 
 */
public interface BorrowDao {
    /**
     * To mark the DVD as borrow
     * 
     * @param borrow
     */
    public void insert(Borrow borrow);
}
