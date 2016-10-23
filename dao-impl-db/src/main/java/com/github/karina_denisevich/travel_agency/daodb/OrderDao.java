package com.github.karina_denisevich.travel_agency.daodb;

import com.github.karina_denisevich.travel_agency.datamodel.Order;

import java.util.List;

public interface OrderDao {

    Order get(Long id);

    void insert(Order entity);

    void update(Order entity);

    void delete(Long id);

    List<Order> getAll();
}
