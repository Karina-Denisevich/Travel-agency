package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.annotation.DbTableAnalyzer;
import com.github.karina_denisevich.travel_agency.daoapi.BookingDao;
import com.github.karina_denisevich.travel_agency.daodb.mapper.BookingWithToursMapper;
import com.github.karina_denisevich.travel_agency.daodb.mapper.TourMapper;
import com.github.karina_denisevich.travel_agency.daodb.unmapper.BookingUnmapper;
import com.github.karina_denisevich.travel_agency.datamodel.Booking;
import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class BookingDaoDbDbImpl extends GenericDaoDbImpl<Booking, Long> implements BookingDao {

    @Inject
    JdbcTemplate jdbcTemplate;

    private final String tableName;
    private final String tourTableName;

    public BookingDaoDbDbImpl() {
        super(new BookingUnmapper());
        this.tableName = new DbTableAnalyzer().getDbTableName(Booking.class);
        this.tourTableName = new DbTableAnalyzer().getDbTableName(Tour.class);
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

    @Override
    public List<Booking> getAllByUserId(Long userId) {
        final String sql = "SELECT * FROM " + tableName + " b "
                + "LEFT JOIN " + tourTableName + " t ON b.tour_id=t.id "
                + "WHERE b.user_id = ?";

        return jdbcTemplate.query(sql, new Object[]{userId},
                new BookingWithToursMapper(new TourMapper()));
    }
}
