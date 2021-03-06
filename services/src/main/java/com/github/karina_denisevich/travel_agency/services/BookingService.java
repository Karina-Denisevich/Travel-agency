package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.Booking;

import java.util.List;

public interface BookingService extends AbstractService<Booking, Long> {

    Booking getByIdWithUser(Long id);

    int deleteByUserId(Long id);

    int deleteByTourId(Long id);

    List<Booking> getAllByUserId(Long userId);
}
