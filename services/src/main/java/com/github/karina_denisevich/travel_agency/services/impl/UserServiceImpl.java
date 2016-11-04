package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daodb.UserDao;
import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import com.github.karina_denisevich.travel_agency.services.BookingService;
import com.github.karina_denisevich.travel_agency.services.RoleService;
import com.github.karina_denisevich.travel_agency.services.UserDetailsService;
import com.github.karina_denisevich.travel_agency.services.UserService;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
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
        beforeInsert(user);

        if (user.getId() == null) {
            Long id = userDao.insert(user);
            LOGGER.info("User is created. Id={}, email={}.", id, user.getEmail());
            return id;
        } else {
            userDao.update(user);
            LOGGER.info("User updated. Id={}, email={}.", user.getId(), user.getEmail());
            return user.getId();
        }
    }

    @Override
    public void saveAll(List<User> users) {
        users.forEach(this::beforeInsert);
        userDao.insertBatch(users);
        LOGGER.info("{} users added.", users.size());
    }

    private void beforeInsert(User user) {
        Validate.notEmpty(user.getEmail(), "Email should not be empty.");
        Validate.notEmpty(user.getPassword(), "Password should not be empty.");
        user.setRole(getUserRole(user));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    }

    /**
     * If user has no role, then returns ROLE_USER by default,
     * if user has role without id, then returns the same role, but with id,
     * in any other case returns role from parameters.
     *
     * @param user User, whose role will be returned
     * @return user's role
     */
    private Role getUserRole(User user) {
        Role role = user.getRole();
        if (role == null) {
            role = roleService.getByType(Role.RoleEnum.valueOf("ROLE_USER"));
        } else if (role.getId() == null) {
            role = roleService.getByType(role.getType());
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
