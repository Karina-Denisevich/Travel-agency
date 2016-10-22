package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daodb.UserDao;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import com.github.karina_denisevich.travel_agency.services.UserService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    UserDao userDao;

    @Override
    public void save(User user) {

        if (user.getId() == null) {
            userDao.insert(user);
        } else {
            userDao.update(user);
        }
    }

    @Override
    public void saveAll(List<User> users) {

    }

    @Override
    public User get(Long id) {
        return null;
    }
}
