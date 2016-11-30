package com.github.karina_denisevich.travel_agency.web.controller;

import com.github.karina_denisevich.travel_agency.daoapi.exception.EmptyResultException;
import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import com.github.karina_denisevich.travel_agency.services.TourService;
import com.github.karina_denisevich.travel_agency.web.dto.TourDto;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/{tourId}", method = RequestMethod.GET)
    public ResponseEntity<TourDto> getById(@PathVariable Long tourId) {
        return new ResponseEntity<>(conversionService.getObject().convert(tourService.get(tourId),
                TourDto.class), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TourDto>> getAll() {
        List<Tour> tours = tourService.getAll();
        if (CollectionUtils.isEmpty(tours)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<TourDto> convertedList = (List<TourDto>) conversionService.getObject().convert(tours,
                TypeDescriptor.valueOf(List.class),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(TourDto.class)));

        return new ResponseEntity<>(convertedList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody TourDto tourDto) {
        Tour tour = (conversionService.getObject().convert(tourDto, Tour.class));
        tourService.save(tour);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{tourId}", method = RequestMethod.POST)
    public ResponseEntity<Void> update(@RequestBody TourDto tourDto,
                                       @PathVariable Long tourId) {
        Tour tour = (conversionService.getObject().convert(tourDto, Tour.class));
        tour.setId(tourId);
        tourService.save(tour);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{tourId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long tourId) {
        tourService.get(tourId);
        tourService.delete(tourId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
