package com.github.karina_denisevich.travel_agency.web.converter.entity_to_dto;

import com.github.karina_denisevich.travel_agency.datamodel.Booking;
import com.github.karina_denisevich.travel_agency.web.dto.BookingDto;
import org.springframework.core.convert.converter.Converter;

public class BookingToDto implements Converter<Booking, BookingDto> {

    @Override
    public BookingDto convert(Booking booking) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(booking.getId());
        bookingDto.setIsConfirmed(booking.getIsConfirmed());
        bookingDto.setOrderDate(booking.getOrderDate());
        if (booking.getUser() != null) {
            bookingDto.setUserDto(new UserToDto().convert(booking.getUser()));
        }
        if (booking.getTour() != null) {
            bookingDto.setTourDto(new TourToDto().convert(booking.getTour()));
        }
        return bookingDto;
    }
}
