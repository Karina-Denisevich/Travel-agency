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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class BookingTest {

    @Inject
    BookingService orderService;

    @Test
    public void insertTest(){
        Booking booking = new Booking();
        User user = new User();
        Tour tour = new Tour();

        user.setEmail("mmm");
        tour.setTitle("Beach tour");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = "2010-10-06";
        try {
            booking.setOrderDate(sdf.parse(dateInString));
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }

        booking.setUser(user);
        booking.setTour(tour);

        Long id = orderService.save(booking);

        Assert.assertNotNull(id);
    }
}
