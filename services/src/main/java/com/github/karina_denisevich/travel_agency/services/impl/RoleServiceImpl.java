package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daodb.RoleDao;
import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import com.github.karina_denisevich.travel_agency.services.RoleService;
import com.github.karina_denisevich.travel_agency.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Inject
    RoleDao roleDao;

    @Inject
    UserService userService;

    @Override
    public Long save(Role role) {

        if (role.getId() == null) {
            return roleDao.insert(role);
        } else {
            roleDao.update(role);
            return role.getId();
        }
    }

    @Override
    public void saveAll(List<Role> roles) {

    }

    @Override
    public Role get(Long id) {

        return roleDao.get(id);
    }

    @Override
    public List<Role> getAll() {

        return roleDao.getAll();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Role role = new Role();
        role.setId(id);
        for (User user : userService.getByRole(role)) {
            userService.delete(user.getId());
        }
        roleDao.delete(id);
    }

    @Override
    public Role getByType(Role.RoleEnum type) {
        return roleDao.getByType(type);
    }
}
