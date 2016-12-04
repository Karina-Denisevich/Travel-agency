package com.github.karina_denisevich.travel_agency.web.converter.entity_to_dto;

import com.github.karina_denisevich.travel_agency.datamodel.Booking;
import com.github.karina_denisevich.travel_agency.web.dto.BookingDto;
import com.github.karina_denisevich.travel_agency.web.dto.TourDto;
import com.github.karina_denisevich.travel_agency.web.dto.UserDto;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import javax.inject.Inject;

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
            bookingDto.setUserDto(conversionService.getObject()
                    .convert(booking.getUser(), UserDto.class));
        }
        if (booking.getTour() != null) {
            bookingDto.setTourDto(conversionService.getObject()
                    .convert(booking.getTour(), TourDto.class));
        }
        return bookingDto;
    }
}
