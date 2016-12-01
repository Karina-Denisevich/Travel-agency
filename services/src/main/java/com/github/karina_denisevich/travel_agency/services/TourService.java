package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.Tour;

import java.util.List;

public interface TourService extends AbstractService<Tour>{
//
//    Long save(Tour tour);
//
//    void saveAll(List<Tour> tours);
//
//    Tour get(Long id);
//
//    List<Tour> getAll();
//
//    void delete(Long id);

    List<Tour> getByTitle(String title);
}
