package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daodb.util.OrderDao;
import com.github.karina_denisevich.travel_agency.datamodel.Order;
import com.github.karina_denisevich.travel_agency.services.util.OrderService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    OrderDao orderDao;

    @Override
    public void save(Order category) {

    }

    @Override
    public void saveAll(List<Order> categories) {

    }

    @Override
    public Order get(Long id) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
