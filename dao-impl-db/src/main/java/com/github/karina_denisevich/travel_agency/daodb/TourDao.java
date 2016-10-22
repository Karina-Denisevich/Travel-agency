package com.github.karina_denisevich.travel_agency.daodb;

import com.github.karina_denisevich.travel_agency.datamodel.Tour;

import java.util.List;

public interface TourDao {

    Tour get(Long id);

    void insert(Tour entity);

    void update(Tour entity);

    void delete(Long id);

    List<Tour> getAll();
}
