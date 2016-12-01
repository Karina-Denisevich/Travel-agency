package com.github.karina_denisevich.travel_agency.web.controller;

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
public class TourController extends AbstractController<Tour, TourDto> {

    @Inject
    private TourService tourService;

    @Inject
    private ConversionServiceFactoryBean conversionService;


}
