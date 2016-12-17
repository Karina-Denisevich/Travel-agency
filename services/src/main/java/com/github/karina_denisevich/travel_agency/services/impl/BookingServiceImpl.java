package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daoapi.BookingDao;
import com.github.karina_denisevich.travel_agency.datamodel.Booking;
import com.github.karina_denisevich.travel_agency.services.BookingService;
import org.apache.commons.lang3.Validate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @PreAuthorize("hasRole('ROLE_ADMIN') or #booking.user.id==authentication.principal.userId")
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void saveAll(List<Booking> bookingList) {
        bookingList.forEach(this::save);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Booking get(Long id) {
        return bookingDao.get(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Booking> getAll() {
        return bookingDao.getAll();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public int delete(Long id) {
        return bookingDao.delete(id);
    }

    @Override
    public Booking getByIdWithUser(Long id) {
        Booking booking = bookingDao.getByIdWithUser(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals(booking.getUser().getEmail()) ||
                auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return booking;
        } else {
            throw new AccessDeniedException("Access is denied");
        }
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId == authentication.principal.userId")
    public int deleteByUserId(Long userId) {
        return bookingDao.deleteByUserId(userId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public int deleteByTourId(Long tourId) {
        return bookingDao.deleteByTourId(tourId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId == authentication.principal.userId")
    public List<Booking> getAllByUserId(Long userId) {
        return bookingDao.getAllByUserId(userId);
    }
}
