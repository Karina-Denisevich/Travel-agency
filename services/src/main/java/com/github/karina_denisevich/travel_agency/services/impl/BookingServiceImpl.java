package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daodb.BookingDao;
import com.github.karina_denisevich.travel_agency.daodb.TourDao;
import com.github.karina_denisevich.travel_agency.daodb.UserDao;
import com.github.karina_denisevich.travel_agency.datamodel.Booking;
import com.github.karina_denisevich.travel_agency.services.BookingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Inject
    BookingDao bookingDao;

    @Inject
    UserDao userDao;

    @Inject
    TourDao tourDao;

    @Transactional
    @Override
    public Long save(Booking booking) {

        if (booking.getConfirmed() == null) {
            booking.setConfirmed(false);
        }
        if (booking.getUser().getId() == null) {
            booking.setUser(userDao.getByEmail(booking.getUser().getEmail()));
        }
        if (booking.getTour().getId() == null) {
            booking.setTour(tourDao.getByTitle(booking.getTour().getTitle()));
        }

        if (booking.getId() == null) {
            return bookingDao.insert(booking);
        } else {
            bookingDao.update(booking);
            return booking.getId();
        }
    }

    @Override
    public void saveAll(List<Booking> categories) {

    }

    @Override
    public Booking get(Long id) {
        return null;
    }

    @Override
    public List<Booking> getAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
