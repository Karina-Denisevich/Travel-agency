package com.github.karina_denisevich.travel_agency.web.controller;

import com.github.karina_denisevich.travel_agency.datamodel.Booking;
import com.github.karina_denisevich.travel_agency.services.BookingService;
import com.github.karina_denisevich.travel_agency.web.dto.BookingDto;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/bookings")
@SuppressWarnings("unchecked")
public class BookingController extends AbstractController<Booking, BookingDto> {

    @Inject
    private BookingService bookingService;

    @Inject
    private ConversionServiceFactoryBean conversionService;

}
