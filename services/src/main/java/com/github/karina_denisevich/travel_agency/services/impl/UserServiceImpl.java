package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daodb.UserDao;
import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import com.github.karina_denisevich.travel_agency.services.BookingService;
import com.github.karina_denisevich.travel_agency.services.RoleService;
import com.github.karina_denisevich.travel_agency.services.UserDetailsService;
import com.github.karina_denisevich.travel_agency.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(UserServiceImpl.class);

    @Inject
    UserDao userDao;

    @Inject
    RoleService roleService;

    @Inject
    BookingService bookingService;

    @Inject
    UserDetailsService userDetailsService;

    @Transactional
    @Override
    public Long save(User user) {
        user.setRole(getUserRole(user));

        if (user.getId() == null) {
            Long id = userDao.insert(user);
            if (id == null) {
                LOGGER.error("User with email={} does't created.", user.getEmail());
            } else {
                LOGGER.info("User created. Id={}, email={}.", id, user.getEmail());
            }
            return id;
        } else {
            userDao.update(user);
            LOGGER.info("User updated. Id={}, email={}.", user.getId(), user.getEmail());
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
        if (role == null || role.getId() == null) {
            return roleService.getByType(Role.RoleEnum.valueOf("ROLE_USER"));
        }
        return role;
    }

    @Override
    public User get(Long id) {
        User user = userDao.get(id);
        if (user == null) {
            LOGGER.info("User with id={} was not found.", id);
        } else {
            LOGGER.info("User with id={} was found.", id);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> userList = userDao.getAll();
        LOGGER.info("{} users was obtained.", userList.size());
        return userList;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        bookingService.deleteByUserId(id);
        LOGGER.info("Booking for user with id={} deleted", id);
        userDetailsService.delete(id);
        LOGGER.info("UserDetails for user with id={} deleted", id);
        userDao.delete(id);
        LOGGER.info("User with id={} deleted", id);
    }

    @Override
    public User getByEmail(String email) {
        User user = userDao.getByEmail(email);
        if (user != null) {
            LOGGER.info("User with email={} was found", email);
        } else {
            LOGGER.info("User with email={} was not found", email);
        }
        return user;
    }

    @Override
    public User getWithRole(Long id) {
        User user = userDao.getWithRole(id);
        if (user != null) {
            LOGGER.info("User with id={}, with role={} was found", id, user.getRole());
        } else {
            LOGGER.info("User with id={} was not found", id);
        }
        return user;
    }

    @Override
    public List<User> getByRole(Role role) {
        List<User> userList = userDao.getByRole(role);
        if (userList.size() > 0) {
            LOGGER.info("{} users was found with role={}", userList.size(), role.getType());
        } else {
            LOGGER.info("There was no user with role={}", role.getType());
        }
        return userList;
    }
}
