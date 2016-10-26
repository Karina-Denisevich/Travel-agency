package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.Tour;

import java.util.List;

public interface TourService {

    void save(Tour tour);

    void saveAll(List<Tour> tours);

    Tour get(Long id);

    List<Tour> getAll();

    void delete(Long id);
}
