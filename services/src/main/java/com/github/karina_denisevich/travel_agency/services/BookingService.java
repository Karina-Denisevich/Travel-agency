package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.Booking;

import java.util.List;

public interface BookingService {

    Long save(Booking booking);

    void saveAll(List<Booking> bookingList);

    Booking get(Long id);

    List<Booking> getAll();

    void delete(Long id);

    Booking getByIdWithUser(Long id);

    void deleteByUserId(Long id);

    void deleteByTourId(Long id);

    List<Booking> getAllByUserId(Long userId);
}
