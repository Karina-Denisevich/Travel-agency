package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daodb.TourDao;
import com.github.karina_denisevich.travel_agency.services.TourService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class TourServiceImpl implements TourService {

    @Inject
    TourDao tourDao;
}
