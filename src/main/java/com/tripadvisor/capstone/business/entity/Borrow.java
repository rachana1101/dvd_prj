package com.tripadvisor.capstone.business.entity;

import java.io.Serializable;
import java.util.UUID;

import org.joda.time.DateTime;

/**
 * DO classes to interact between api and database
 * @author rachana
 * @since  Aug 9, 2014
 * 
 */
public class Borrow implements Serializable {
    /**
     * serial id 
     */
    private static final long serialVersionUID = 4562942697895157133L;
    
    private String dvdTitle;
    private UUID dvdId;
    private UUID userId;
    private DateTime dueDate;
    private DateTime borrowedOn;
    /**
     * @return the dvdTitle
     */
    public String getDvdTitle() {
        return dvdTitle;
    }
    /**
     * @param dvdTitle the dvdTitle to set
     */
    public void setDvdTitle(String dvdTitle) {
        this.dvdTitle = dvdTitle;
    }
    /**
     * @return the userId
     */
    public UUID getUserId() {
        return userId;
    }
    /**
     * @param userId the userId to set
     */
    public void setUserId(UUID userId) {
        this.userId = userId;
    }
    /**
     * @return the dueDate
     */
    public DateTime getDueDate() {
        return dueDate;
    }
    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(DateTime dueDate) {
        this.dueDate = dueDate;
    }
    /**
     * @return the borrowedOn
     */
    public DateTime getBorrowedOn() {
        return borrowedOn;
    }
    /**
     * @param borrowedOn the borrowedOn to set
     */
    public void setBorrowedOn(DateTime borrowedOn) {
        this.borrowedOn = borrowedOn;
    }
    /**
     * @return the dvdId
     */
    public UUID getDvdId() {
        return dvdId;
    }
    /**
     * @param dvdId the dvdId to set
     */
    public void setDvdId(UUID dvdId) {
        this.dvdId = dvdId;
    }
   
}
