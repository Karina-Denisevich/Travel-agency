package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.daodb.TourDao;
import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TourDaoImpl implements TourDao {

    @Override
    public Tour get(Long id) {
        return null;
    }

    @Override
    public void insert(Tour entity) {

    }

    @Override
    public void update(Tour entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Tour> getAll() {
        return null;
    }
}
