package com.github.karina_denisevich.travel_agency.daodb.unmapper;

import com.github.karina_denisevich.travel_agency.datamodel.Booking;

import java.util.LinkedHashMap;
import java.util.Map;

public class BookingUnmapper implements RowUnmapper<Booking> {

    @Override
    public Map<String, Object> mapColumns(Booking booking) {

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("order_date", booking.getOrderDate());
        map.put("confirmed", booking.getConfirmed());
        map.put("user_id", booking.getUser().getId());
        map.put("tour_id", booking.getTour().getId());
        if (booking.getId() != null) {
            map.put("id", booking.getId());
        }
        return map;
    }
}
