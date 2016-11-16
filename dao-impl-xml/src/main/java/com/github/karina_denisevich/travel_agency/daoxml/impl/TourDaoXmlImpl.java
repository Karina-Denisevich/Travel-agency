package com.github.karina_denisevich.travel_agency.daoxml.impl;

import com.github.karina_denisevich.travel_agency.daoapi.TourDao;
import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TourDaoXmlImpl extends GenericDaoXmlImpl<Tour, Long> implements TourDao {

    @Override
    public List<Tour> getByTitle(String title) {
        return null;
    }

    @Override
    public void update(Tour entity) {

    }

    @Override
    public void delete(Long id) {

    }
}
