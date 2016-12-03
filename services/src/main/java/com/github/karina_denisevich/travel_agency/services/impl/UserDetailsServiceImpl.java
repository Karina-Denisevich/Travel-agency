package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daoapi.UserDetailsDao;
import com.github.karina_denisevich.travel_agency.daoapi.exception.EmptyResultException;
import com.github.karina_denisevich.travel_agency.datamodel.UserDetails;
import com.github.karina_denisevich.travel_agency.services.UserDetailsService;
import com.github.karina_denisevich.travel_agency.services.UserService;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserDetailsServiceImpl extends AbstractServiceImpl<UserDetails, Long>
        implements UserDetailsService {

    @Inject
    private UserDetailsDao userDetailsDao;

    @Transactional
    @Override
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
        Validate.notEmpty(userDetails.getFirstName(), "First name should not be empty.");
        Validate.notEmpty(userDetails.getLastName(), "Last name should not be empty.");

        if (userDetails.getDiscount() == null) {
            userDetails.setDiscount(0.0);
        }
    }

//    @Transactional
//    @Override
//    public void saveAll(List<UserDetails> userDetailsList) {
//        userDetailsList.forEach(this::save);
//    }

    @Override
    public UserDetails get(Long id) {
        return super.get(id);
        //return userDetailsDao.get(id);
    }

    @Override
    public List<UserDetails> getAll() {
        return super.getAll();
       // return userDetailsDao.getAll();
    }

    @Override
    public int delete(Long id) {
        return super.delete(id);
        //return userDetailsDao.delete(id);
    }
}
