package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.Role;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class RoleTest {

    @Inject
    RoleService roleService;

    @Test
    public void insert(){
        Role role = new Role();
        role.setType(Role.RoleEnum.ROLE_ANONYMOUS);
        Long pk = roleService.save(role);

        Assert.assertNotNull(pk);
    }

    @Test
    @Ignore
    public void updateTest(){
        Role role = new Role();
        role.setId(1L);
        role.setType(Role.RoleEnum.ROLE_ADMIN);
        Long pk = roleService.save(role);

        Assert.assertNotNull(pk);
    }
}
