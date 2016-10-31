package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.annotation.DbTableAnalyzer;
import com.github.karina_denisevich.travel_agency.daodb.BookingDao;
import com.github.karina_denisevich.travel_agency.daodb.unmapper.BookingUnmapper;
import com.github.karina_denisevich.travel_agency.datamodel.Booking;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class BookingDaoImpl extends GenericDaoImpl<Booking, Long> implements BookingDao {

    @Inject
    JdbcTemplate jdbcTemplate;

    private final String tableName;

    public BookingDaoImpl() {

        super(new BookingUnmapper());
        this.tableName = new DbTableAnalyzer().getDbTableName(Booking.class);
    }

    @Override
    public void deleteByUserId(Long id) {
        final String sql = "DELETE FROM " + tableName + " WHERE user_id = ?";

        jdbcTemplate.update(sql, id);
    }

    @Override
    public void deleteByTourId(Long id) {
        final String sql = "DELETE FROM " + tableName + " WHERE tour_id = ?";

        jdbcTemplate.update(sql, id);
    }
}
