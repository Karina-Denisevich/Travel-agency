package com.github.karina_denisevich.travel_agency.web.converter.entity_to_dto;

import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import com.github.karina_denisevich.travel_agency.web.dto.CategoryDto;
import com.github.karina_denisevich.travel_agency.web.dto.TourDto;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import javax.inject.Inject;
import java.util.List;

public class TourToDto implements Converter<Tour, TourDto> {

    @Inject
    private ConversionServiceFactoryBean conversionService;

    @Override
    @SuppressWarnings("unchecked")
    public TourDto convert(Tour tour) {
        TourDto tourDto = new TourDto();
        tourDto.setId(tour.getId());
        tourDto.setTitle(tour.getTitle());
        tourDto.setIsHot(tour.getIsHot());
        tourDto.setPhotoLink(tour.getPhotoLink());
        tourDto.setPrice(tour.getPrice());
        tourDto.setDescription(tour.getDescription());
        tourDto.setCategoryList((List<CategoryDto>) conversionService.getObject()
                .convert(tour.getCategoryList(), TypeDescriptor.valueOf(List.class),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(CategoryDto.class))));
        return tourDto;
    }
}
