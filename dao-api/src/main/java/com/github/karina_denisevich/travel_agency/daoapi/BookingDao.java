package com.github.karina_denisevich.travel_agency.daoapi;

import com.github.karina_denisevich.travel_agency.datamodel.Booking;

import java.util.List;

public interface BookingDao extends GenericDao<Booking, Long> {

    int deleteByUserId(Long id);

    int deleteByTourId(Long id);

    Booking getByIdWithUser(Long id);

    List<Booking> getAllByUserId(Long userId);
}
