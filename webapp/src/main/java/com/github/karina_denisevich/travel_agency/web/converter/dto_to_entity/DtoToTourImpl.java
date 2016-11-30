package com.github.karina_denisevich.travel_agency.web.converter.dto_to_entity;

import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import com.github.karina_denisevich.travel_agency.web.dto.TourDto;
import org.springframework.core.convert.converter.Converter;

public class DtoToTourImpl implements Converter<TourDto, Tour> {

    @Override
    public Tour convert(TourDto tourDto) {
        Tour tour = new Tour();
        tour.setId(tourDto.getId());
        tour.setTitle(tourDto.getTitle());
        tour.setIsHot(tourDto.getIsHot());
        tour.setPhotoLink(tourDto.getPhotoLink());
        tour.setPrice(tourDto.getPrice());
        tour.setDescription(tourDto.getDescription());
        tour.setCategoryList(tourDto.getCategoryList());
        return tour;
    }
}
