package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daodb.RoleDao;
import com.github.karina_denisevich.travel_agency.daodb.UserDao;
import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import com.github.karina_denisevich.travel_agency.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    UserDao userDao;

    @Inject
    RoleDao roleDao;

    @Transactional
    @Override
    public Long save(User user) {
        user.setRole(getUserRole(user));

        if (user.getId() == null) {
            return userDao.insert(user);
        } else {
            userDao.update(user);
            return user.getId();
        }
    }

    @Override
    public void saveAll(List<User> users) {
        for (User user : users) {
            user.setRole(getUserRole(user));
        }
        userDao.insertBatch(users);
    }

    private Role getUserRole(User user) {
        Role role = user.getRole();
        if (role == null) {
            return roleDao.getByType(Role.RoleEnum.valueOf("ROLE_USER"));
        } else if (role.getId() == null) {
            return roleDao.getByType(Role.RoleEnum.valueOf("ROLE_USER"));
        }
        return role;
    }

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public User getWithRole(Long id) {
        return userDao.getWithRole(id);
    }
}
