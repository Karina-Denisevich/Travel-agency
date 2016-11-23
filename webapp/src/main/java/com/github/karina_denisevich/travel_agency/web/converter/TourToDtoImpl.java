package com.github.karina_denisevich.travel_agency.web.converter;

import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import com.github.karina_denisevich.travel_agency.web.dto.TourDto;
import org.springframework.stereotype.Component;

@Component
public class TourToDtoImpl implements EntityToDto<Tour, TourDto> {

    @Override
    public TourDto convert(Tour tour) {
        TourDto tourDto = new TourDto();
        tourDto.setId(tour.getId());
        tourDto.setTitle(tour.getTitle());
        tourDto.setIsHot(tour.getIsHot());
        tourDto.setPhotoLink(tour.getPhotoLink());
        tourDto.setPrice(tour.getPrice());
        tourDto.setDescription(tour.getDescription());
        return tourDto;
    }
}
