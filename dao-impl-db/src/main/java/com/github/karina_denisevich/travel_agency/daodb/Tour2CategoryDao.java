package com.github.karina_denisevich.travel_agency.daodb;

import com.github.karina_denisevich.travel_agency.datamodel.Tour;

public interface Tour2CategoryDao {

    void insertBatch(Tour tour);

    void deleteByTourId(Long id);

    void deleteByCategoryId(Long id);
}
