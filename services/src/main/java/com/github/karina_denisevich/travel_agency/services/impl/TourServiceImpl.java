package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daodb.TourDao;
import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import com.github.karina_denisevich.travel_agency.services.TourService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class TourServiceImpl implements TourService {

    @Inject
    TourDao tourDao;

    @Override
    public Long save(Tour tour) {
        return null;
    }

    @Override
    public void saveAll(List<Tour> tours) {

    }

    @Override
    public Tour get(Long id) {
        return null;
    }

    @Override
    public List<Tour> getAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
