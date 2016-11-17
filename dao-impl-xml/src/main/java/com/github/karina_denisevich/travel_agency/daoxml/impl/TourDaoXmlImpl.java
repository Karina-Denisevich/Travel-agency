package com.github.karina_denisevich.travel_agency.daoxml.impl;

import com.github.karina_denisevich.travel_agency.daoapi.TourDao;
import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TourDaoXmlImpl extends GenericDaoXmlImpl<Tour, Long> implements TourDao {

    @Override
    public List<Tour> getByTitle(String title) {
        List<Tour> entityList = xmlFileIOUtils.readCollection();

        return entityList.stream()
                .filter(tour -> tour.getTitle().equals(title)).collect(Collectors.toList());
    }

}
