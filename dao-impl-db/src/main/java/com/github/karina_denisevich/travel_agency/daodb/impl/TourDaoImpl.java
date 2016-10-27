package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.daodb.TourDao;
import com.github.karina_denisevich.travel_agency.daodb.unmapper.TourUnmapper;
import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class TourDaoImpl extends GenericDaoImpl<Tour, Long> implements TourDao {

    @Inject
    JdbcTemplate jdbcTemplate;

    public TourDaoImpl(){
        super(new TourUnmapper());
    }

    @Override
    public void insertBatch(List<Tour> tours) {
    }
}
