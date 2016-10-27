package com.github.karina_denisevich.travel_agency.daodb.unmapper;

import com.github.karina_denisevich.travel_agency.datamodel.Order;

import java.util.Map;

public class OrderUnmapper implements RowUnmapper<Order> {

    @Override
    public Map<String, Object> mapColumns(Order order) {
        return null;
    }
}
