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
        String dateInString = "1980-10-08";
        try {
            userDetails.setbDate(sdf.parse(dateInString));
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
        userDetails.setPhone("+375292222222");
        userDetails.setFirstName("Name2");
        userDetails.setLastName("LName2");
        User user = new User();
        user.setEmail("updated");
        userDetails.setUser(user);

        Long descId = userDetailsService.save(userDetails);

        Assert.assertNotNull(descId);
        Assert.assertEquals(userService.getByEmail("updated").getId(), descId);
    }

    @Test
    public void userDetailsTest() {
        UserDetails userDetails = userDetailsService.get(40L);

        Assert.assertEquals("n", userDetails.getFirstName());
    }
}
