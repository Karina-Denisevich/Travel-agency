package com.github.karina_denisevich.travel_agency.web.converter.entity_to_dto;

import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import com.github.karina_denisevich.travel_agency.web.dto.TourDto;
import org.springframework.core.convert.converter.Converter;

public class TourToDtoImpl implements Converter<Tour, TourDto> {

    @Override
    public TourDto convert(Tour tour) {
        TourDto tourDto = new TourDto();
        tourDto.setId(tour.getId());
        tourDto.setTitle(tour.getTitle());
        tourDto.setIsHot(tour.getIsHot());
        tourDto.setPhotoLink(tour.getPhotoLink());
        tourDto.setPrice(tour.getPrice());
        tourDto.setDescription(tour.getDescription());
        tourDto.setCategoryList(tour.getCategoryList());  //TODO: change to Dto
        return tourDto;
    }
}
