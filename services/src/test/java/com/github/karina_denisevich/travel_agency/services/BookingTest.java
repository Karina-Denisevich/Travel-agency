package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.Booking;
import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class BookingTest {

    @Inject
    BookingService bookingService;

    @Test
    public void getByIdTest() {
        Long id = 2L;
        Booking booking = bookingService.get(id);

        Assert.assertNotNull("booking for id=" + id + " should not be null", booking);
        Assert.assertEquals(id, booking.getId());
    }

    @Test
    public void getAllTest() {
        List<Booking> bookingList = bookingService.getAll();
        Assert.assertNotNull("bookings' list should not be null", bookingList);
    }

    @Test
    public void insertTest() {
        Booking booking = new Booking();
        User user = new User();
        Tour tour = new Tour();

        user.setId(7L);
        tour.setId(3L);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = "2004-11-06";
        try {
            booking.setOrderDate(sdf.parse(dateInString));
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }

        booking.setUser(user);
        booking.setTour(tour);

        Long id = bookingService.save(booking);

        Assert.assertNotNull(id);
    }

    @Test
    public void updateTest() {
        User user = new User();
        user.setId(2L);

        Tour tour = new Tour();
        tour.setId(2L);

        Booking booking = new Booking();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = "2011-12-06";
        try {
            booking.setOrderDate(sdf.parse(dateInString));
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
        booking.setId(2L);
        booking.setUser(user);
        booking.setTour(tour);

        Long id = bookingService.save(booking);

        Assert.assertNotNull(id);
    }

    @Test
    public void getOrdersByUserIdTest() {
        Long userId = 2L;
        List<Booking> bookingList = bookingService.getAllByUserId(userId);
    }

    @Test
    public void getAllByUserIdTest(){
        Long id = 7L;

        List<Booking> bookingList = bookingService.getAllByUserId(id);

        Assert.assertNotNull(bookingList);
    }
}
