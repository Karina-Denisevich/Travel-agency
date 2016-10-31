package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.annotation.DbTableAnalyzer;
import com.github.karina_denisevich.travel_agency.daodb.TourDao;
import com.github.karina_denisevich.travel_agency.daodb.unmapper.TourUnmapper;
import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class TourDaoImpl extends GenericDaoImpl<Tour, Long> implements TourDao {

    @Inject
    JdbcTemplate jdbcTemplate;

    private final String tableName;

    public TourDaoImpl() {
        super(new TourUnmapper());
        this.tableName = new DbTableAnalyzer().getDbTableName(Tour.class);
    }

    @Override
    public Tour getByTitle(String title) {
        final String sql = "SELECT * FROM " + tableName + " WHERE title = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{title},
                new BeanPropertyRowMapper<>(Tour.class));
    }
}
