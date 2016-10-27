package com.github.karina_denisevich.travel_agency.daodb.unmapper;

import com.github.karina_denisevich.travel_agency.datamodel.Tour;

import java.util.Map;

public class TourUnmapper implements RowUnmapper<Tour> {

    @Override
    public Map<String, Object> mapColumns(Tour tour) {
        return null;
    }
}
