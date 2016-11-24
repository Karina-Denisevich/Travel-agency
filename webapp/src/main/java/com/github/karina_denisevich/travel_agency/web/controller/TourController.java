package com.github.karina_denisevich.travel_agency.web.controller;

import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import com.github.karina_denisevich.travel_agency.services.TourService;
import com.github.karina_denisevich.travel_agency.web.converter.entity_to_dto.EntityToDto;
import com.github.karina_denisevich.travel_agency.web.dto.TourDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/tours")
public class TourController {

    @Inject
    private TourService tourService;

    @Inject
    private EntityToDto<Tour, TourDto> converterToDto;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TourDto>> getAll() {
        return new ResponseEntity<>(converterToDto.convert(tourService.getAll()), HttpStatus.OK);
    }
}
