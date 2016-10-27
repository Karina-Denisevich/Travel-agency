package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daodb.UserDetailsDao;
import com.github.karina_denisevich.travel_agency.datamodel.UserDetails;
import com.github.karina_denisevich.travel_agency.services.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Inject
    UserDetailsDao userDetailsDao;

    @Override
    public Long save(UserDetails userDetails) {
        if (userDetails.getDiscount() == null) {
            userDetails.setDiscount(0.0);
        }
        if (userDetails.getId() == null) {
            return userDetailsDao.insert(userDetails);
        } else {
            userDetailsDao.update(userDetails);
            return userDetails.getId();
        }
    }

    @Override
    public void saveAll(List<UserDetails> userDetailses) {

    }

    @Override
    public UserDetails get(Long id) {
        return userDetailsDao.get(id);
    }

    @Override
    public List<UserDetails> getAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
