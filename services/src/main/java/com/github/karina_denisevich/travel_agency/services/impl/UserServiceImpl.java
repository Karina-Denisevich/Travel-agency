package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daoapi.UserDao;
import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import com.github.karina_denisevich.travel_agency.services.BookingService;
import com.github.karina_denisevich.travel_agency.services.RoleService;
import com.github.karina_denisevich.travel_agency.services.UserDetailsService;
import com.github.karina_denisevich.travel_agency.services.UserService;
import org.apache.commons.lang3.Validate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

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
            if (userDao.getByEmail(user.getEmail()) != null) {
                //TODO: add exc
            }
            return userDao.insert(user);
        } else {
            userDao.update(user);
            return user.getId();
        }
    }

    @Transactional
    @Override
    public void saveAll(List<User> users) {
        users.forEach(this::beforeInsert);
        userDao.insertBatch(users);
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
        return userDao.get(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        bookingService.deleteByUserId(id);
        userDetailsService.delete(id);
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

    @Override
    public List<User> getByRole(Role role) {
        if (role.getId() == null) {
            role = roleService.getByType(role.getType());
        }
        return userDao.getByRole(role);
    }
}
