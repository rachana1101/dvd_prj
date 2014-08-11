package com.tripadvisor.capstone.external;

import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.joda.time.DateTime;

import com.tripadvisor.capstone.business.entity.Status;

/**
 * Assumption : Each DVD only contains 1 movie.
 * 
 * @author rachana
 * @since  Aug, 8 2014
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class DVDTo {
    private UUID id;
    private UUID movieId;
    private Double price;
    private UUID userId;
    private String title;
    private String descripton;
    private DateTime addedOn;
    private Status status;
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
    /**
     * @return the id
     */
    public UUID getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(UUID id) {
        this.id = id;
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
    public void setMovieId(UUID movieId) {
        this.movieId = movieId;
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
    public void setPrice(Double price) {
        this.price = price;
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
     * @return the descripton
     */
    public String getDescripton() {
        return descripton;
    }
    /**
     * @param descripton the descripton to set
     */
    public void setDescripton(String descripton) {
        this.descripton = descripton;
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
    public void setAddedOn(DateTime addedOn) {
        this.addedOn = addedOn;
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
    public void setStatus(Status status) {
        this.status = status;
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
    public void setTitle(String title) {
        this.title = title;
    }
}
