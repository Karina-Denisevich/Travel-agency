package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.daoapi.exception.EmptyResultException;
import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import org.junit.*;
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

    private Long id;
    private String email;

    @Before
    public void executeBeforeEachTest() {
        User user = new User();
        user.setEmail("TEST");
        email = user.getEmail();
        user.setPassword("1111");
        try {
            id = userService.save(user);
        } catch (DuplicateKeyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void updateTest() {
        User user = new User();
        user.setId(id);
        user.setEmail("Updated");
        user.setPassword("2222");
        id = userService.save(user);

        Assert.assertEquals(user, userService.get(id));
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
        Assert.assertNotNull("users' list should not be null", userList);
    }

    @Test
    @Ignore
    public void insertTest() {
        User user = new User();

        //validate email before
        user.setEmail("Ivan@gmail.com");
        user.setPassword("1111");

        Role role = new Role();
        role.setId(1L);
        role.setType(Role.RoleEnum.ROLE_ADMIN);

        user.setRole(role);

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
        user.setEmail("Kate@mail.ru");
        user.setPassword("111111");
        Role role = new Role();
        role.setType(Role.RoleEnum.ROLE_USER);
        user.setRole(role);

        User user1 = new User();
        user1.setEmail("Yan@mail.ru");
        user1.setPassword("111111dd");

        userList.add(user);
        userList.add(user1);

        userService.saveAll(userList);

        Assert.assertTrue("Size should be more than 1.", userService.getAll().size() >= 2);
    }

    @Test
    public void getWithRoleTest() {
        User user = userService.getWithRole(id);
        Assert.assertNotNull("user for id=" + id + " should not be null", user);
        Assert.assertNotNull("role for id=" + id + " should not be null", user.getRole());
        Assert.assertEquals(id, user.getId());
    }

    @Test
    public void getByRoleTest() {
        Role role = new Role();
        role.setType(Role.RoleEnum.ROLE_USER);
        List<User> userList = userService.getByRole(role);

        Assert.assertTrue("Size should be grater than 0. ", userList.size() > 0);
    }

    @Test
    public void getByEmailTest() {
        User user = userService.getByEmail(email);

        Assert.assertEquals(id, user.getId());
    }

    @Test(expected = EmptyResultException.class)
    public void deleteTest() {
        User user = new User();
        user.setEmail("testDelete");
        email = user.getEmail();
        user.setPassword("1111");
        Long id = userService.save(user);

        userService.delete(id);
        userService.get(id);
    }

    @After
    public void executeAfter() {
        userService.delete(id);
    }
}
