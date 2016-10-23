package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.User;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class SpringRunner {

    @Inject
    UserService userService;

    Long id;
    User user;

    @Before
    public void executeBeforeEachTest() {
        user = new User();
        user.setEmail("Test");
        user.setPassword("test");
        user.setRoleId(1L);
        userService.save(user);
        id = userService.getByEmail("Test").getId();
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
    public void deleteTest() {
        userService.delete(id);
    }

    @Test
    public void insertTest() {
        User user = new User();
        user.setEmail("Test1");
        user.setPassword("test1");
        user.setRoleId(1L);

        userService.save(user);
    }

    @After
    public void executeAfter() {
        userService.delete(id);
    }
}
