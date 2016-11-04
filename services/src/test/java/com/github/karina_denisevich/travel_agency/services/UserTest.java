package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.User;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class UserTest {

    @Inject
    UserService userService;

    Long id;

    //@Before
    public void executeBeforeEachTest() {
        User user = new User();
        user.setEmail("TEST");
        user.setPassword("111111");
        try {
            id = userService.save(user);
        } catch (DuplicateKeyException e) {
            id = -1L;
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void updateTest() {
        User user = new User();
        user.setId(40L);
        user.setEmail("NewName");
        user.setPassword("111111");
        try {
            id = userService.save(user);
        } catch (DuplicateKeyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getByIdTest() {
        Long id = 155L;
        User user = userService.get(id);

        Assert.assertNotNull("user for id=" + id + " should not be null", user);
        Assert.assertEquals(id, user.getId());
    }

    @Test
    public void getAllTest() {
        List<User> userList = userService.getAll();
        Assert.assertNotNull("users' list should not be null", userList);
    }

    @Test
    @Ignore
    public void deleteTest() {
        Long id = 49L;
        userService.delete(id);
    }


    @Test
    //@Ignore
    public void insertTest() {
        User user = new User();

        //validate email before
        user.setEmail("Ivan@gmail.com");
        user.setPassword("1111");

        Long id = null;
        try {
            id = userService.save(user);
        } catch (DuplicateKeyException e) {
            System.out.println(e.getCause().getMessage());
        }
        Assert.assertNotNull(id);
        User userFromDb = userService.get(id);
        Assert.assertEquals(user.getEmail(), userFromDb.getEmail());
        // userService.delete(id);
    }

    @Test
    @Ignore
    public void insertBatchTest() {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setEmail("mmm");
        user.setPassword("111111");

        User user1 = new User();
        user1.setEmail("nnn");
        user1.setPassword("111111dd");

        userList.add(user);
        userList.add(user1);

        userService.saveAll(userList);
    }

    @Test
    public void getWithRoleTest() {
        Long id = 155L;
        User user = userService.getWithRole(id);
        Assert.assertNotNull("user for id=" + id + " should not be null", user);
        Assert.assertNotNull("role for id=" + id + " should not be null", user.getRole());
        Assert.assertEquals(id, user.getId());
    }

    @Test
    public void getWithBookingsTest(){

    }

    // @After
    public void executeAfter() {
        userService.delete(id);
    }
}
