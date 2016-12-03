package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daoapi.BookingDao;
import com.github.karina_denisevich.travel_agency.datamodel.Booking;
import com.github.karina_denisevich.travel_agency.services.BookingService;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Inject
    private BookingDao bookingDao;

    @Transactional
    @Override
    public Long save(Booking booking) {
        beforeSave(booking);

        if (booking.getId() == null) {
            return bookingDao.insert(booking);
        } else {
            if (bookingDao.update(booking) == 0) {
                return null;
            }
            return booking.getId();
        }
    }

    private void beforeSave(Booking booking) {
        Validate.notNull(booking.getUser(), "User should not be null");
        Validate.notNull(booking.getTour(), "Tour should not be null");
        Validate.notNull(booking.getOrderDate(), "Date should not be null");

        if (booking.getIsConfirmed() == null) {
            booking.setIsConfirmed(false);
        }
    }

    @Transactional
    @Override
    public void saveAll(List<Booking> bookingList) {
        bookingList.forEach(this::save);
    }

    @Override
    public Booking get(Long id) {
        return bookingDao.get(id);
    }

    @Override
//    @PostFilter("@bookingServiceImpl.getByIdWithUser(filterObject.id).user.email" +
//            "==authentication.name or hasRole('ROLE_ADMIN')")
    public List<Booking> getAll() {
        return bookingDao.getAll();
    }

    @Override
    public int delete(Long id) {
        return bookingDao.delete(id);
    }

    @Override
    public Booking getByIdWithUser(Long id) {
        return bookingDao.getByIdWithUser(id);
    }

    @Override
    public int deleteByUserId(Long id) {
        return bookingDao.deleteByUserId(id);
    }

    @Override
    public int deleteByTourId(Long id) {
        return bookingDao.deleteByTourId(id);
    }

    @Override
    public List<Booking> getAllByUserId(Long userId) {
        return bookingDao.getAllByUserId(userId);
    }
}
