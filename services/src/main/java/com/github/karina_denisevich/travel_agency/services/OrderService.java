package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.Order;

import java.util.List;

public interface OrderService {

    Long save(Order category);

    void saveAll(List<Order> categories);

    Order get(Long id);

    List<Order> getAll();

    void delete(Long id);
}
