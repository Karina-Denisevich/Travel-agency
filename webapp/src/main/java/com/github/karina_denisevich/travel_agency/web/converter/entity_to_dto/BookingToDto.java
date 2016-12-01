package com.github.karina_denisevich.travel_agency.web.converter.entity_to_dto;

import com.github.karina_denisevich.travel_agency.datamodel.Booking;
import com.github.karina_denisevich.travel_agency.web.dto.BookingDto;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import javax.inject.Inject;
import java.text.SimpleDateFormat;

public class BookingToDto implements Converter<Booking, BookingDto> {

    @Inject
    private ConversionServiceFactoryBean conversionService;

    @Override
    public BookingDto convert(Booking booking) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(booking.getId());
        bookingDto.setIsConfirmed(booking.getIsConfirmed());
        bookingDto.setOrderDate(conversionService.getObject()
                .convert(booking.getOrderDate(), String.class));

        if (booking.getUser() != null) {
            bookingDto.setUserDto(new UserToDto().convert(booking.getUser()));
        }
        if (booking.getTour() != null) {
            bookingDto.setTourDto(new TourToDto().convert(booking.getTour()));
        }
        return bookingDto;
    }
}
