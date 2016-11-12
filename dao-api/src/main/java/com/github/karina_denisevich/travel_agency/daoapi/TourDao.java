package com.github.karina_denisevich.travel_agency.daoapi;

import com.github.karina_denisevich.travel_agency.datamodel.Tour;

import java.util.List;

public interface TourDao extends GenericDao<Tour, Long> {

    List<Tour> getByTitle(String title);
}
