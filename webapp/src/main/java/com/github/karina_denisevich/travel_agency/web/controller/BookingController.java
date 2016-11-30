package com.github.karina_denisevich.travel_agency.web.controller;

import com.github.karina_denisevich.travel_agency.daoapi.exception.EmptyResultException;
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
public class BookingController {

    @Inject
    private BookingService bookingService;

    @Inject
    private ConversionServiceFactoryBean conversionService;

    @RequestMapping(value = "/{bookingId}", method = RequestMethod.GET)
    public ResponseEntity<BookingDto> getById(@PathVariable Long bookingId) {
        return new ResponseEntity<>(conversionService.getObject().convert(bookingService.get(bookingId),
                BookingDto.class), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<BookingDto>> getAll() {
        List<Booking> bookings = bookingService.getAll();
        if (CollectionUtils.isEmpty(bookings)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<BookingDto> convertedList = (List<BookingDto>) conversionService.getObject().convert(bookings,
                TypeDescriptor.valueOf(List.class),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(BookingDto.class)));

        return new ResponseEntity<>(convertedList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody BookingDto bookingDto) {
        Booking booking = (conversionService.getObject().convert(bookingDto, Booking.class));
        bookingService.save(booking);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{bookingId}", method = RequestMethod.POST)
    public ResponseEntity<Void> update(@RequestBody BookingDto bookingDto,
                                       @PathVariable Long bookingId) {
        Booking booking = (conversionService.getObject().convert(bookingDto, Booking.class));
        booking.setId(bookingId);
        bookingService.save(booking);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{bookingId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long bookingId) {
        bookingService.get(bookingId);
        bookingService.delete(bookingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
