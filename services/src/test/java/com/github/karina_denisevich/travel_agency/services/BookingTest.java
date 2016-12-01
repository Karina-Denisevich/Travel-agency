package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.Booking;
import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context-test.xml")
public class BookingTest {

    @Inject
    private BookingService bookingService;

    @Inject
    private UserService userService;

    @Inject
    private TourService tourService;

    private Long id;
    private Long userId;
    private Long tourId;

    @Before
    //@Test
    public void insertTest() {
        User user = new User();
        user.setEmail("BookingTest@mail.ru");
        user.setPassword("1");
        userId = userService.save(user);
        user.setId(userId);

        Tour tour = new Tour();
        tour.setTitle("BookingTestTitle");
        tour.setPrice(100.0);
        tourId = tourService.save(tour);
        tour.setId(tourId);

        Booking booking = new Booking();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = "2004-11-06";
        try {
            booking.setOrderDate(sdf.parse(dateInString));
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }

        booking.setUser(user);
        booking.setTour(tour);

        id = bookingService.save(booking);

        Assert.assertNotNull(id);
    }

    @Test
    public void getByIdTest() {
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
    public void updateTest() {
        User user = new User();
        user.setId(userId);

        Tour tour = new Tour();
        tour.setId(tourId);

        Booking booking = new Booking();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = "2011-12-06";
        try {
            booking.setOrderDate(sdf.parse(dateInString));
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
        booking.setId(id);
        booking.setUser(user);
        booking.setTour(tour);

        Long id = bookingService.save(booking);

        Assert.assertNotNull(id);
    }

    @Test
    public void getAllByUserIdTest() {
        List<Booking> bookingList = bookingService.getAllByUserId(userId);

        Assert.assertNotNull(bookingList);
    }

    @Test
    public void getByIdWithUser() {
        Booking booking = bookingService.getByIdWithUser(id);
        Assert.assertEquals(userId, booking.getUser().getId());
    }

    @After
    public void delete() {
        userService.delete(userId);
        tourService.delete(tourId);
        bookingService.delete(id);
    }
}
