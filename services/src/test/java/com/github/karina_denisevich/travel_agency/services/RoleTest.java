package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.Role;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class RoleTest {

    @Inject
    RoleService roleService;

    private Long id;

    @Before
    //@Test
    public void insert() {
        Role role = new Role();
        role.setType(Role.RoleEnum.ROLE_ANONYMOUS);
        id = roleService.save(role);

        Assert.assertNotNull(id);
    }

    @Test
    public void getByIdTest() {
        Role role = roleService.get(id);

        Assert.assertNotNull("role for id=" + id + " should not be null", role);
        Assert.assertEquals(id, role.getId());
    }

    @Test
    public void getAllTest() {
        List<Role> roleList = roleService.getAll();
        Assert.assertNotNull("roles' list should not be null", roleList);
    }

    @Test
    public void updateTest() {
        Long id = 1L;
        Role role = new Role();
        role.setId(id);
        role.setType(Role.RoleEnum.ROLE_ADMIN);
        id = roleService.save(role);

        Assert.assertNotNull(id);
    }

    @Test
    public void getByTypeTest() {
        Role role = roleService.getByType(Role.RoleEnum.ROLE_ANONYMOUS);
        Assert.assertEquals(id, role.getId());
    }

    @After
    //@Test
    public void deleteTest() {
        roleService.delete(id);
    }
}
