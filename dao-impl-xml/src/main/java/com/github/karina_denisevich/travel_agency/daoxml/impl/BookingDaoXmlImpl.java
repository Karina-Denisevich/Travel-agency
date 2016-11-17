package com.github.karina_denisevich.travel_agency.daoxml.impl;

import com.github.karina_denisevich.travel_agency.daoapi.BookingDao;
import com.github.karina_denisevich.travel_agency.datamodel.Booking;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookingDaoXmlImpl extends GenericDaoXmlImpl<Booking, Long> implements BookingDao {

    @Override
    public void deleteByUserId(Long id) {
        List<Booking> bookingList = xmlFileIOUtils.readCollection();

        xmlFileIOUtils.writeCollection(bookingList);
    }

    @Override
    public void deleteByTourId(Long id) {
        List<Booking> bookingList = xmlFileIOUtils.readCollection();

        Iterator iterator = bookingList.iterator();
        while (iterator.hasNext()) {
            Booking booking = (Booking) iterator.next();
            if (booking.getTour().getId().equals(id)) {
                iterator.remove();
            }
        }
        xmlFileIOUtils.writeCollection(bookingList);
    }

    @Override
    public List<Booking> getAllByUserId(Long userId) {
        List<Booking> bookingList = xmlFileIOUtils.readCollection();
        return bookingList.stream()
                .filter(booking -> booking.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }
}
