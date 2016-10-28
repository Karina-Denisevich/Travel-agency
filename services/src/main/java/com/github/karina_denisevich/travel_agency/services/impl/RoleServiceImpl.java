package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daodb.RoleDao;
import com.github.karina_denisevich.travel_agency.daodb.UserDao;
import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import com.github.karina_denisevich.travel_agency.services.RoleService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Inject
    RoleDao roleDao;

    @Inject
    UserDao userDao;

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
        return null;
    }

    @Override
    public List<Role> getAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
