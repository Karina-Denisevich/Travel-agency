package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.daodb.BookingDao;
import com.github.karina_denisevich.travel_agency.daodb.unmapper.BookingUnmapper;
import com.github.karina_denisevich.travel_agency.datamodel.Booking;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class BookingDaoImpl extends GenericDaoImpl<Booking, Long> implements BookingDao {

    @Inject
    JdbcTemplate jdbcTemplate;

    public BookingDaoImpl(){
        super(new BookingUnmapper());
    }

    @Override
    public void insertBatch(List<Booking> bookings) {
    }
}
