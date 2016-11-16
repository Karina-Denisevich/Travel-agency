package com.github.karina_denisevich.travel_agency.daoxml.impl;

import com.github.karina_denisevich.travel_agency.daoapi.BookingDao;
import com.github.karina_denisevich.travel_agency.datamodel.Booking;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookingDaoXmlImpl extends GenericDaoXmlImpl<Booking, Long> implements BookingDao {

    @Override
    public void update(Booking entity) {

    }

    @Override
    public void deleteByUserId(Long id) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteByTourId(Long id) {

    }

    @Override
    public List<Booking> getAllByUserId(Long userId) {
        return null;
    }
}
