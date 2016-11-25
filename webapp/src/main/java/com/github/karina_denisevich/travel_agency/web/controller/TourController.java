package com.github.karina_denisevich.travel_agency.web.controller;

import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import com.github.karina_denisevich.travel_agency.services.TourService;
import com.github.karina_denisevich.travel_agency.web.dto.TourDto;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/tours")
@SuppressWarnings("unchecked")
public class TourController {

    @Inject
    private TourService tourService;

    @Inject
    private ConversionServiceFactoryBean conversionService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TourDto>> getAll() {
        List<Tour> tours = tourService.getAll();
        if (CollectionUtils.isEmpty(tours)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<TourDto> convertedList = (List<TourDto>) conversionService.getObject().convert(tours,
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Tour.class)),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(TourDto.class)));

        return new ResponseEntity<>(convertedList, HttpStatus.OK);
    }
}
