package com.github.karina_denisevich.travel_agency.daodb.mapper;

import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TourMapper implements RowMapper<Tour> {

    @Override
    public Tour mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tour tour = new Tour();
        tour.setId(rs.getLong("id"));
        tour.setTitle(rs.getString("title"));
        tour.setIsHot(rs.getBoolean("hot"));
        tour.setPhotoLink(rs.getString("photo_link"));
        tour.setPrice(rs.getDouble("price"));
        tour.setDescription(rs.getString("description"));

        return tour;
    }
}
