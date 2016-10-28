package com.github.karina_denisevich.travel_agency.daodb.mapper;

import com.github.karina_denisevich.travel_agency.datamodel.Booking;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingMapper implements RowMapper<Booking> {

    @Override
    public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }
}
