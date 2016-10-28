package com.github.karina_denisevich.travel_agency.daodb;

import com.github.karina_denisevich.travel_agency.datamodel.Tour;

public interface TourDao extends GenericDao<Tour, Long> {

    Tour getByTitle(String title);
}
