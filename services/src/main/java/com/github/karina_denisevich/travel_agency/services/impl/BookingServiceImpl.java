package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daodb.BookingDao;
import com.github.karina_denisevich.travel_agency.datamodel.Booking;
import com.github.karina_denisevich.travel_agency.services.BookingService;
import com.github.karina_denisevich.travel_agency.services.TourService;
import com.github.karina_denisevich.travel_agency.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Inject
    BookingDao bookingDao;

    @Inject
    UserService userService;

    @Inject
    TourService tourService;

    @Transactional
    @Override
    public Long save(Booking booking) {

        if (booking.getConfirmed() == null) {
            booking.setConfirmed(false);
        }
        if (booking.getUser().getId() == null) {
            booking.setUser(userService.getByEmail(booking.getUser().getEmail()));
        }
        if (booking.getTour().getId() == null) {
            booking.setTour(tourService.getByTitle(booking.getTour().getTitle()));
        }

        if (booking.getId() == null) {
            return bookingDao.insert(booking);
        } else {
            bookingDao.update(booking);
            return booking.getId();
        }
    }

    @Override
    public void saveAll(List<Booking> bookingList) {
        bookingList.forEach(this::save);
    }

    @Override
    public Booking get(Long id) {
        return bookingDao.get(id);
    }

    @Override
    public List<Booking> getAll() {
        return bookingDao.getAll();
    }

    @Override
    public void delete(Long id) {
        bookingDao.delete(id);
    }

    @Override
    public void deleteByUserId(Long id) {
        bookingDao.deleteByUserId(id);
    }

    @Override
    public void deleteByTourId(Long id) {
        bookingDao.deleteByTourId(id);
    }
}
