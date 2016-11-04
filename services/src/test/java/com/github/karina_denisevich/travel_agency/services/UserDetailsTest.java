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
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class UserDetailsTest {

    @Inject
    UserDetailsService userDetailsService;

    @Inject
    UserService userService;

    @Test
    public void getByIdTest() {
        Long id = 2L;
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
        userDetails.setFirstName("Ivan");
        userDetails.setLastName("Ivanov");
        User user = new User();
        Long id = 3L;
        user.setId(id);
        userDetails.setUser(user);

        Long descId = userDetailsService.save(userDetails);

        Assert.assertNotNull(descId);
        Assert.assertEquals(userService.get(id).getId(), descId);
    }

    @Test
    public void updateTest() {
        Long id = 2L;
        UserDetails userDetails = new UserDetails();
        userDetails.setId(id);
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

        Assert.assertEquals(userDetails.getFirstName(), userDetailsService.get(id).getFirstName());
    }

    @Test
    public void deleteTest(){
        userDetailsService.delete(3L);
    }
}
