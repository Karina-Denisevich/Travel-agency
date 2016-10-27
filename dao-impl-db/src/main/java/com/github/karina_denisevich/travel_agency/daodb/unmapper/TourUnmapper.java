package com.github.karina_denisevich.travel_agency.daodb.unmapper;

import com.github.karina_denisevich.travel_agency.datamodel.Tour;

import java.util.LinkedHashMap;
import java.util.Map;

public class TourUnmapper implements RowUnmapper<Tour> {

    @Override
    public Map<String, Object> mapColumns(Tour tour) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("title", tour.getTitle());
        map.put("photo_link", tour.getPhotoLink());
        map.put("hot", tour.getIsHot());
        map.put("price", tour.getPrice());
        map.put("description", tour.getDescription());
        if (tour.getId() != null) {
            map.put("id", tour.getId());
        }

        return map;
    }
}
