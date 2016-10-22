package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daodb.UserDetailsDao;
import com.github.karina_denisevich.travel_agency.services.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Inject
    UserDetailsDao userDetailsDao;
}
