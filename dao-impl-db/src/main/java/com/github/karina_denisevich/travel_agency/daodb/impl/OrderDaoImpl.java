package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.daodb.OrderDao;
import com.github.karina_denisevich.travel_agency.daodb.mapper.OrderMapper;
import com.github.karina_denisevich.travel_agency.daodb.unmapper.OrderUnmapper;
import com.github.karina_denisevich.travel_agency.datamodel.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order, Long> implements OrderDao {

    @Inject
    JdbcTemplate jdbcTemplate;

    public OrderDaoImpl(){
        super(new OrderUnmapper());
    }

    @Override
    public void update(Order entity) {

    }

    @Override
    public void insertBatch(List<Order> orders) {
    }
}
