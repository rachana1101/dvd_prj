package com.tripadvisor.capstone.business.dao;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.tripadvisor.capstone.business.entity.DVD;
import com.tripadvisor.capstone.business.entity.Status;

/**
 * @author rachana
 * @since Aug 10, 2014
 * 
 */
public interface DVDDao {
    /**
     * To find the DVD by Id
     * 
     * @param id
     *            {@link UUID}
     * @return {@link DVD}
     */
    public DVD findById(UUID id);

    /**
     * To insert the DVD information into table
     * 
     * @param dvd
     *            {@link DVD}
     */
    public void insert(DVD dvd);

    /**
     * To update the status of DVD to either Fired, Available, Taken Note : I am
     * using isolation level TRANSACTION_SERIALIZABLE to avoid concurrent thread
     * updating the same row at the same time
     * 
     * @param id
     *            {@link UUID}
     * @param status
     *            {@link Status}
     */
    public void updateStatus(UUID id, Status status);

    /**
     * To search the DVD
     * Assumption : Text search is equals not likes
     * @param whereMapper
     *            {@link Map}
     * @return {@link List}
     */
    public List<DVD> search(Map<String, Object> whereMapper);
    /**
     * Find the available dvds by Title, same method can be overloaded to find the available DVDs by other attributes
     * Note : not adding ordering to it as it can slow down the query
     * @param title
     * @return
     */
    public List<DVD> findAvailableDVDs(String title);
}
