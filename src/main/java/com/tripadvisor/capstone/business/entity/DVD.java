package com.tripadvisor.capstone.business.entity;

import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;

/**
 * Assumption : Each DVD only contains 1 movie.
 * 
 * TODO : add equals, hashcode and toString
 * 
 * @author rachana
 * @since  Aug, 8 2014
 */
public class DVD implements Serializable {
    /**
     * serial id
     */
    private static final long serialVersionUID = 1L;
    private UUID id;
    private UUID movieId;
    private Double price;
    private UUID userId;
    private String title;
    private String descripton;
    private DateTime addedOn;
    private Status status; 
    /**
     * @return the id
     */
    public UUID getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public DVD setId(UUID id) {
        this.id = id;
        return this;
    }
    /**
     * @return the movieId
     */
    public UUID getMovieId() {
        return movieId;
    }
    /**
     * @param movieId the movieId to set
     */
    public DVD setMovieId(UUID movieId) {
        this.movieId = movieId;
        return this;
    }
    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }
    /**
     * @param price the price to set
     */
    public DVD setPrice(Double price) {
        this.price = price;
        return this;
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
    public DVD setUserId(UUID userId) {
        this.userId = userId;
        return this;
    }
    /**
     * @return the descripton
     */
    public String getDescripton() {
        return descripton;
    }
    /**
     * @param descripton the descripton to set
     */
    public DVD setDescripton(String descripton) {
        this.descripton = descripton;
        return this;
    }
    /**
     * @return the addedOn
     */
    public DateTime getAddedOn() {
        return addedOn;
    }
    /**
     * @param addedOn the addedOn to set
     */
    public DVD setAddedOn(DateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }
    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public DVD setStatus(Status status) {
        this.status = status;
        return this;
    }
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param title the title to set
     */
    public DVD setTitle(String title) {
        this.title = title;
        return this;
    }
    /**
     * String representation of the DVD content
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
