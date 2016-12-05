package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daoapi.UserDao;
import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import com.github.karina_denisevich.travel_agency.services.BookingService;
import com.github.karina_denisevich.travel_agency.services.RoleService;
import com.github.karina_denisevich.travel_agency.services.UserDetailsService;
import com.github.karina_denisevich.travel_agency.services.UserService;
import org.apache.commons.lang3.Validate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Inject
    private RoleService roleService;

    @Inject
    private BookingService bookingService;

    @Inject
    private UserDetailsService userDetailsService;

    @Transactional
    @Override
    @CacheEvict(value = "userAuthBasic", allEntries = true)
//    @PreAuthorize("#user.id == null or hasRole('ROLE_ADMIN')" +
//            " or @userServiceImpl.get(#user.id).email == authentication.name")
    public Long save(User user) {
        if (user.getId() == null) {
            return userDao.insert(user);
        } else {
            if (userDao.update(user) == 0) {
                return null;
            }
            return user.getId();
        }
    }

    @Transactional
    @Override
    @CacheEvict(value = "userAuthSpring", allEntries = true)
//    @PreAuthorize("#user.id==null or hasRole('ROLE_ADMIN')")
    public void saveAll(List<User> users) {
        users.forEach(this::beforeSave);
        userDao.insertBatch(users);
    }

    private void beforeSave(User user) {
        Validate.notEmpty(user.getEmail(), "Email should not be empty.");
        Validate.notEmpty(user.getPassword(), "Password should not be empty.");
        user.setRole(getUserRole(user));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    }

    @Override
    //@PreAuthorize("hasRole('ROLE_USER')")
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Transactional
    @Override
    @CacheEvict(value = "userAuthBasic", allEntries = true)
    public int delete(Long id) {
        bookingService.deleteByUserId(id);
        userDetailsService.delete(id);
        return userDao.delete(id);
    }

    /**
     * If user has no role, then returns ROLE_USER by default,
     * if user has role without id, then returns the same role, but with id,
     * in any other case returns role from parameters.
     *
     * @param user User, whose role will be returned
     * @return user'servlet role
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

    @Override
    public User getByEmailWithRole(String email) {
        return userDao.getByEmailWithRole(email);
    }
}
