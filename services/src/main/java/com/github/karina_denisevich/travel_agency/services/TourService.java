package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.Tour;

import java.util.List;

public interface TourService extends AbstractService<Tour, Long>{

    List<Tour> getByTitle(String title);
}
