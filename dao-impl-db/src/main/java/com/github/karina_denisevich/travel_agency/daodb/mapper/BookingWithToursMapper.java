package com.github.karina_denisevich.travel_agency.daodb.mapper;

import com.github.karina_denisevich.travel_agency.datamodel.Booking;
import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingWithToursMapper implements RowMapper<Booking> {

    private final TourMapper tourMapper;

    public BookingWithToursMapper(TourMapper tourMapper) {
        this.tourMapper = tourMapper;
    }

    @Override
    public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
        Booking booking = new Booking();
        booking.setId(rs.getLong("id"));
        booking.setOrderDate(rs.getDate("order_date"));
        booking.setConfirmed(rs.getBoolean("confirmed"));

        Tour tour = this.tourMapper.mapRow(rs, rowNum);
        booking.setTour(tour);

        return booking;
    }
}
