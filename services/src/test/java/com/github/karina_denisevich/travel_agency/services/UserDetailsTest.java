package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.User;
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
        String dateInString = "1992-10-18";
        try {
            userDetails.setbDate(sdf.parse(dateInString));
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
        userDetails.setPhone("+375292442222");
        userDetails.setFirstName("Name4");
        userDetails.setLastName("LName4");
        User user = new User();
        user.setId(104L); //setEmail();
        userDetails.setUser(user);

        Long descId = userDetailsService.save(userDetails);

        Assert.assertNotNull(descId);
        Assert.assertEquals(userService.get(104L).getId(), descId);
    }

    @Test
    public void updateTest() {
        UserDetails userDetails = new UserDetails();
        userDetails.setId(40L);
        userDetails.setFirstName("f");
        userDetails.setLastName("l");
        userDetails.setPhone("+375290000000");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = "1985-11-08";
        try {
            userDetails.setbDate(sdf.parse(dateInString));
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
        userDetailsService.save(userDetails);

        Assert.assertEquals(userDetails.getFirstName(), userDetailsService.get(40L).getFirstName());
    }
}
