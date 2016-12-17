package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daoapi.UserDetailsDao;
import com.github.karina_denisevich.travel_agency.daoapi.exception.EmptyResultException;
import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import com.github.karina_denisevich.travel_agency.datamodel.UserDetails;
import com.github.karina_denisevich.travel_agency.services.UserDetailsService;
import com.github.karina_denisevich.travel_agency.services.UserService;
import org.apache.commons.lang3.Validate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Inject
    private UserDetailsDao userDetailsDao;

    @Inject
    private UserService userService;

    @Transactional
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or #userDetails.id == authentication.principal.userId")
    public Long save(UserDetails userDetails) {
        beforeSave(userDetails);

        if (userDetails.getId() == null) {
            userDetails.setId(userDetails.getUser().getId());
            return userDetailsDao.insert(userDetails);
        } else {
            try {
                userDetailsDao.get(userDetails.getId());
            } catch (EmptyResultException ex) {
                return userDetailsDao.insert(userDetails);
            }
            if (userDetailsDao.update(userDetails) == 0) {
                return null;
            }
            return userDetails.getId();
        }
    }

    private void beforeSave(UserDetails userDetails) {
        User user = userService.get(userDetails.getId() != null ?
                userDetails.getId() : userDetails.getUser().getId());
        Validate.notEmpty(userDetails.getFirstName(), "First name should not be empty.");
        Validate.notEmpty(userDetails.getLastName(), "Last name should not be empty.");

        if (user.getRole().getType() == Role.RoleEnum.ROLE_ADMIN) {
            if (userDetails.getDiscount() == null) {
                userDetails.setDiscount(0.0);
            }
        } else if (userDetails.getId() == null) {
            userDetails.setDiscount(0.0);
        } else {
            userDetails.setDiscount(userDetailsDao.get(userDetails.getId()).getDiscount());
        }
    }

    @Transactional
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void saveAll(List<UserDetails> userDetailsList) {
        userDetailsList.forEach(this::save);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or #id == authentication.principal.userId")
    public UserDetails get(Long id) {
        return userDetailsDao.get(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserDetails> getAll() {
        return userDetailsDao.getAll();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or #id == authentication.principal.userId")
    public int delete(Long id) {
        return userDetailsDao.delete(id);
    }
}
