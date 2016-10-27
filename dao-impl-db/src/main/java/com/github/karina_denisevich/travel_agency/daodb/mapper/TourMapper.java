package com.github.karina_denisevich.travel_agency.daodb.mapper;

import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TourMapper implements RowMapper<Tour> {

    @Override
    public Tour mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }
}
