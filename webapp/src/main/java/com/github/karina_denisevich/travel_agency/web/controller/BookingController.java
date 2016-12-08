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
public class BookingController extends AbstractController<Booking, BookingDto, Long> {

    @Inject
    private BookingService bookingService;

    @Inject
    private ConversionServiceFactoryBean conversionService;

    @RequestMapping(value = "/{bookingId}", method = RequestMethod.GET, params = "withUser")
    public ResponseEntity<BookingDto> getWithUserById(@PathVariable Long bookingId,
                                                      @RequestParam(value = "withUser", defaultValue = "false") Boolean isWithUser) {
        if (isWithUser) {
            return new ResponseEntity<>(conversionService.getObject()
                    .convert(bookingService.getByIdWithUser(bookingId), BookingDto.class), HttpStatus.OK);
        }
        return super.getById(bookingId, null);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<List<BookingDto>> getAllByUserId(@RequestParam(value = "userId", required = false) Long userId) {
        List<Booking> bookingList;
        if (userId != null) {
            bookingList = bookingService.getAllByUserId(userId);
        } else {
            bookingList = bookingService.getAll();
        }

        if (CollectionUtils.isEmpty(bookingList)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<BookingDto> convertedList = (List<BookingDto>) conversionService.getObject()
                .convert(bookingList, TypeDescriptor.valueOf(List.class),
                        TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(BookingDto.class)));

        return new ResponseEntity<>(convertedList, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteByUserId(@PathVariable Long userId) {
        if (bookingService.deleteByUserId(userId) == 0) {
            return new ResponseEntity<>("There is no booking with userId = " + userId,
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/tours/{tourId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteByTourId(@PathVariable Long tourId) {
        if (bookingService.deleteByTourId(tourId) == 0) {
            return new ResponseEntity<>("There is no booking with tourId = " + tourId,
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
