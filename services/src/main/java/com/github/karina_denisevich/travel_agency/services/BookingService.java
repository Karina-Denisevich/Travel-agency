package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.Booking;

import java.util.List;

public interface BookingService extends AbstractService<Booking> {

    Booking getByIdWithUser(Long id);

    void deleteByUserId(Long id);

    void deleteByTourId(Long id);

    List<Booking> getAllByUserId(Long userId);
}
