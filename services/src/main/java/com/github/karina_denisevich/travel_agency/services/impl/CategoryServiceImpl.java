package com.github.karina_denisevich.travel_agency.services.impl;


import com.github.karina_denisevich.travel_agency.daodb.CategoryDao;
import com.github.karina_denisevich.travel_agency.services.CategoryService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Inject
    CategoryDao categoryDao;
}
