package com.github.karina_denisevich.travel_agency.daoapi;

import com.github.karina_denisevich.travel_agency.datamodel.Booking;

import java.util.List;

public interface BookingDao extends GenericDao<Booking, Long> {

    void deleteByUserId(Long id);

    void deleteByTourId(Long id);

    List<Booking> getAllByUserId(Long userId);
}