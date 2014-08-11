package com.tripadvisor.capstone.business.dao.impl;

import static org.fest.assertions.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.sql.DataSource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.tripadvisor.capstone.business.dao.Constants;
import com.tripadvisor.capstone.business.dao.DVDDao;
import com.tripadvisor.capstone.business.entity.DVD;
import com.tripadvisor.capstone.business.entity.Status;

/**
 * @author rachana
 * @since Aug 9, 2014
 * 
 */
@Component
public class DVDDaoImpl implements DVDDao {

    @Autowired
    private DataSource dataSource;

    /**
     * @param dataSource
     *            the dataSource to set
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        assertThat(this.dataSource).isNotNull();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tripadvisor.capstone.business.dao.DVDDao#findById(java.util.UUID)
     */
    public DVD findById(UUID id) {
        String sql = "SELECT " + Constants.DVD.FIELDS + " FROM "
                + Constants.DVD.TABLE + " d join "
                + Constants.DVD_MOVIE_MAPPING.TABLE
                + " m on d.id = m.dvd_id WHERE id = ?";
        return (DVD) new JdbcTemplate(dataSource).queryForObject(sql,
                new Object[] { id.toString() }, new DVDRowMapper());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tripadvisor.capstone.business.dao.DVDDao#insert(com.tripadvisor.capstone
     * .business.entity.DVD)
     */
    public void insert(DVD dvd) {
        // validate the attributes
        assertThat(dvd).isNotNull();
        assertThat(dvd.getMovieId()).isNotNull();
        String sql = "INSERT INTO "
                + Constants.DVD.TABLE
                + " (`id`, `price`, `user_id`, `status`, `description`) VALUES (?, ?, ?, ?, ?) ";
        UUID dvdId = UUID.randomUUID();
        new JdbcTemplate(dataSource).update(
                sql,
                new Object[] { dvdId.toString(), dvd.getPrice(),
                        dvd.getUserId(), dvd.getStatus().getValue(),
                        dvd.getDescripton() });
        sql = "INSERT INTO " + Constants.DVD_MOVIE_MAPPING.TABLE
                + " (`dvd_id`, `movie_id`) VALUES (? , ?)";
        new JdbcTemplate(dataSource).update(sql,
                new Object[] { dvdId.toString(), dvd.getMovieId().toString() });
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tripadvisor.capstone.business.dao.DVDDao#updateStatus(java.util.UUID,
     * com.tripadvisor.capstone.business.entity.Status)
     */
    public void updateStatus(UUID id, Status status) {
        assertThat(id).isNotNull();
        String sql = "UPDATE " + Constants.DVD.TABLE
                + " SET `status` = ? where id = ? ";
        JdbcTemplate update = new JdbcTemplate(dataSource);
        try {
            update.getDataSource()
                    .getConnection()
                    .setTransactionIsolation(
                            Connection.TRANSACTION_SERIALIZABLE);
            update.update(sql,
                    new Object[] { status.getValue(), id.toString() });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tripadvisor.capstone.business.dao.DVDDao#search(java.util.Map)
     */
    public List<DVD> search(Map<String, Object> whereMapper) {
        // Note : below logic can be taken to the common utils
        String whereClause = null;
        if (!MapUtils.isEmpty(whereMapper)) {
            whereClause = " WHERE ";
            StringBuilder w = new StringBuilder();
            Iterator<Entry<String, Object>> entries = whereMapper.entrySet()
                    .iterator();
            while (entries.hasNext()) {
                Entry<String, Object> thisEntry = (Entry<String, Object>) entries
                        .next();
                Object key = thisEntry.getKey();
                Object value = thisEntry.getValue();
                if (value != null) {
                    if (Constants.DVD.ALIASED_MAPPING.containsKey(key)) {
                        w.append(Constants.DVD.ALIASED_MAPPING.get(key));
                    }
                    if (Constants.Movie.ALIASED_MAPPING.containsKey(key)) {
                        w.append(Constants.Movie.ALIASED_MAPPING.get(key));
                    }
                    if (value.getClass().isAssignableFrom(String.class)
                            || value.getClass()
                                    .isAssignableFrom(DateTime.class)) {
                        w.append(" = '" + value + "'");
                    } else {
                        w.append(" = " + value);
                    }
                    if (entries.hasNext()) {
                        w.append(" AND ");
                    }
                }
            }
            whereClause += w.toString();
        }

        String sql = " SELECT d.id id, price, user_id, status, description, added_on, movie_id "
                + "  FROM dvd d join dvd_movie_mapping dm on d.id = dm.dvd_id join movie m on dm.movie_id=m.id "
                + whereClause;
        List<DVD> dvds = new JdbcTemplate(dataSource).query(sql,
                new DVDRowMapper());
        return dvds;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tripadvisor.capstone.business.dao.DVDDao#findAvailableDVDs(java.lang
     * .String)
     */
    public List<DVD> findAvailableDVDs(String title) {
        String sql = "SELECT id from " + Constants.DVD.TABLE
                + " where title like ? AND status != ? AND status != ? ";
        @SuppressWarnings({ "unchecked", "rawtypes" })
        List<String> dvdIds = new JdbcTemplate(dataSource).query(
                sql,
                new Object[] { title, Status.FRIED.getValue(),
                        Status.TAKEN.toString() }, new BeanPropertyRowMapper(
                        String.class));
        if (CollectionUtils.isNotEmpty(dvdIds)) {
            List<DVD> result = new ArrayList<DVD>();
            for (String id : dvdIds) {
                result.add(this.findById(UUID.fromString(id)));
            }
            return result;
        } else {
            return Collections.emptyList();
        }
    }
}
