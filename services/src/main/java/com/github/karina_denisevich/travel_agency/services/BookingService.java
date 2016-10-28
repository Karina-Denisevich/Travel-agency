package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.Booking;

import java.util.List;

public interface BookingService {

    Long save(Booking booking);

    void saveAll(List<Booking> bookings);

    Booking get(Long id);

    List<Booking> getAll();

    void delete(Long id);
}
