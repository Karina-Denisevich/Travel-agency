package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daodb.RoleDao;
import com.github.karina_denisevich.travel_agency.services.RoleService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class RoleServiceImpl implements RoleService{

    @Inject
    RoleDao roleDao;
}
