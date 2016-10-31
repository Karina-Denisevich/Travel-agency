package com.github.karina_denisevich.travel_agency.daodb;

import com.github.karina_denisevich.travel_agency.datamodel.Tour;

public interface TourToCategoryDao {

    void insertTourWithCategories(Tour tour);

    void deleteByTourId(Long id);

    void deleteByCategoryId(Long id);
}
