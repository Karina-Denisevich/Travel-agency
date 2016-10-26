package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.daodb.util.OrderDao;
import com.github.karina_denisevich.travel_agency.datamodel.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Override
    public Order get(Long id) {
        return null;
    }

    @Override
    public Long insert(Order entity) {
        return null;
    }

    @Override
    public void update(Order entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public void insertBatch(List<Order> orders) {}
}
