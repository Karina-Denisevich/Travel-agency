package com.github.karina_denisevich.travel_agency.web.converter.dto_to_entity;

import com.github.karina_denisevich.travel_agency.datamodel.Booking;
import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import com.github.karina_denisevich.travel_agency.web.dto.BookingDto;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import javax.inject.Inject;
import java.util.Date;

public class DtoToBooking implements Converter<BookingDto, Booking> {

    @Inject
    private ConversionServiceFactoryBean conversionService;

    @Override
    public Booking convert(BookingDto bookingDto) {
        Booking booking = new Booking();
        booking.setId(bookingDto.getId());
        booking.setIsConfirmed(bookingDto.getIsConfirmed());
        booking.setOrderDate(conversionService.getObject()
                .convert(bookingDto.getOrderDate(), Date.class));
        if (bookingDto.getUserDto() != null) {
            booking.setUser(conversionService.getObject()
                    .convert(bookingDto.getUserDto(), User.class));
        }
        if (bookingDto.getTourDto() != null) {
            booking.setTour(conversionService.getObject()
                    .convert(bookingDto.getTourDto(), Tour.class));
        }
        return booking;
    }
}
