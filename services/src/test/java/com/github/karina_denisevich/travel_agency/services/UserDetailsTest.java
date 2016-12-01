package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.User;
import com.github.karina_denisevich.travel_agency.datamodel.UserDetails;
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
public class UserDetailsTest {

    @Inject
    private UserDetailsService userDetailsService;

    @Inject
    private UserService userService;

    private Long id;

    @Before
    public void executeBeforeTest() {
        User user = new User();
        user.setEmail("userDetails");
        user.setPassword("1111");
        id = userService.save(user);
        user.setId(id);

        UserDetails userDetails = new UserDetails();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = "1992-10-18";
        try {
            userDetails.setbDate(sdf.parse(dateInString));
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
        userDetails.setPhone("+375292442222");
        userDetails.setFirstName("Ivan");
        userDetails.setLastName("Ivanov");
        userDetails.setUser(user);

        Long descId = userDetailsService.save(userDetails);

        Assert.assertNotNull(descId);
        Assert.assertEquals(userService.get(id).getId(), descId);
    }

    @Test
    public void getByIdTest() {
        UserDetails userDetails = userDetailsService.get(id);

        Assert.assertNotNull("userDetails for id=" + id + " should not be null", userDetails);
        Assert.assertEquals(id, userDetails.getId());
    }

    @Test
    public void getAllTest() {
        List<UserDetails> userDetailsList = userDetailsService.getAll();
        Assert.assertNotNull("user details list should not be null", userDetailsList);
    }

    @Test
    public void updateTest() {
        UserDetails userDetails = new UserDetails();
        userDetails.setId(id);
        userDetails.setFirstName("userDetailsUpdate");
        userDetails.setLastName("last");
        userDetails.setPhone("+375290000000");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = "1985-11-08";
        try {
            userDetails.setbDate(sdf.parse(dateInString));
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
        userDetailsService.save(userDetails);

        Assert.assertEquals(userDetails.getFirstName(), userDetailsService.get(id).getFirstName());
    }

    @After
    public void deleteTest() {
        userService.delete(id);
    }
}
