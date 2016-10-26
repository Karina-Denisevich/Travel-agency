package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.User;
import com.github.karina_denisevich.travel_agency.services.util.UserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class SpringRunner {

    @Inject
    UserService userService;

    Long id;

    @Before
    public void executeBeforeEachTest() {
        User user = new User();
        user.setEmail("TEST");
        user.setPassword("111111");
        id = userService.save(user);
    }

    @Test
    public void getByIdTest() {
        User user = userService.get(id);

        Assert.assertNotNull("user for id=" + id + " should not be null", user);
        Assert.assertEquals(id, user.getId());
    }

    @Test
    public void getAllTest() {
        List<User> userList = userService.getAll();

        Assert.assertNotNull("user' list should not be null", userList);
    }

    @Test
    @Ignore
    public void deleteTest() {
        userService.delete(id);
    }

    @Test
    public void insertTest() {
        User user = new User();
        user.setEmail("bla99bla");
        user.setPassword("111111");

        Long id = userService.save(user);

        Assert.assertNotNull(id);

        User userFromDb = userService.get(id);

        Assert.assertEquals(user.getEmail(), userFromDb.getEmail());
        userService.delete(id);
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
        User user = userService.getWithRole(id);
        Assert.assertNotNull("user for id=" + id + " should not be null", user);
        Assert.assertNotNull("role for id=" + id + " should not be null", user.getRole());
        Assert.assertEquals(id, user.getId());
    }

    @After
    public void executeAfter() {
        userService.delete(id);
    }
}
