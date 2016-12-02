package com.github.karina_denisevich.travel_agency.web.converter.dto_to_entity;

import com.github.karina_denisevich.travel_agency.datamodel.Category;
import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import com.github.karina_denisevich.travel_agency.web.dto.TourDto;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import javax.inject.Inject;
import java.util.List;

public class DtoToTour implements Converter<TourDto, Tour> {

    @Inject
    private ConversionServiceFactoryBean conversionService;

    @Override
    @SuppressWarnings("unchecked")
    public Tour convert(TourDto tourDto) {
        Tour tour = new Tour();
        tour.setId(tourDto.getId());
        tour.setTitle(tourDto.getTitle());
        tour.setIsHot(tourDto.getIsHot());
        tour.setPhotoLink(tourDto.getPhotoLink());
        tour.setPrice(tourDto.getPrice());
        tour.setDescription(tourDto.getDescription());
        tour.setCategoryList((List<Category>) conversionService.getObject()
                .convert(tourDto.getCategoryList(), TypeDescriptor.valueOf(List.class),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Category.class))));
        return tour;
    }
}
