package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daodb.UserDetailsDao;
import com.github.karina_denisevich.travel_agency.datamodel.UserDetails;
import com.github.karina_denisevich.travel_agency.services.UserDetailsService;
import com.github.karina_denisevich.travel_agency.services.UserService;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Inject
    UserDetailsDao userDetailsDao;

    @Inject
    UserService userService;

    @Transactional
    @Override
    public Long save(UserDetails userDetails) {
        Validate.notEmpty(userDetails.getFirstName(), "First name should not be empty.");
        Validate.notEmpty(userDetails.getLastName(), "Last name should not be empty.");

        if (userDetails.getDiscount() == null) {
            userDetails.setDiscount(0.0);
        }
        if (userDetails.getId() == null) {
            userDetails.setId(userDetails.getUser().getId());
            return userDetailsDao.insert(userDetails);
        } else {
            userDetailsDao.update(userDetails);
            return userDetails.getId();
        }
    }

    @Override
    public void saveAll(List<UserDetails> userDetailsList) {
        userDetailsList.forEach(this::save);
    }

    @Override
    public UserDetails get(Long id) {
        return userDetailsDao.get(id);
    }

    @Override
    public List<UserDetails> getAll() {
        return userDetailsDao.getAll();
    }

    @Override
    public void delete(Long id) {
        userDetailsDao.delete(id);
    }
}
