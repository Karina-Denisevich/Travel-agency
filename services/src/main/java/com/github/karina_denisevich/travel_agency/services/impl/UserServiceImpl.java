package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daodb.UserDao;
import com.github.karina_denisevich.travel_agency.services.UserService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    UserDao userDao;
}
