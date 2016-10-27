package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.UserDetails;
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
public class UserDetailsTest {

    @Inject
    UserDetailsService userDetailsService;

    @Inject
    UserService userService;

    @Test
    public void insertTest() {
        UserDetails userDetails = new UserDetails();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = "1990-11-08";
        try {
            userDetails.setbDate(sdf.parse(dateInString));
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
        userDetails.setPhone("+375291111111");
        userDetails.setFirstName("Name1");
        userDetails.setLastName("LName");
        userDetails.setUser(userService.get(103L));
        Long descId = userDetailsService.save(userDetails);

        Assert.assertEquals(descId, userDetailsService.get(descId).getId());
    }

    @Test
    public void userDetailsTest() {
        UserDetails userDetails = userDetailsService.get(40L);

        Assert.assertEquals("n", userDetails.getFirstName());
    }
}
