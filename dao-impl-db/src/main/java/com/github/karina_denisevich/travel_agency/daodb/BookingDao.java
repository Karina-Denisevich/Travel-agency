package com.github.karina_denisevich.travel_agency.daodb;

import com.github.karina_denisevich.travel_agency.datamodel.Booking;

public interface BookingDao extends GenericDao<Booking, Long> {

    void deleteByUserId(Long id);
    void deleteByTourId(Long id);
}
