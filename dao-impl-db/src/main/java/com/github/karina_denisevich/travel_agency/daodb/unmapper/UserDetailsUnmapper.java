package com.github.karina_denisevich.travel_agency.daodb.unmapper;

import com.github.karina_denisevich.travel_agency.datamodel.UserDetails;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserDetailsUnmapper implements RowUnmapper<UserDetails> {

    @Override
    public Map<String, Object> mapColumns(UserDetails userDetails) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("first_name", userDetails.getFirstName());
        map.put("last_name", userDetails.getLastName());
        map.put("discount", userDetails.getDiscount());
        map.put("bdate", userDetails.getbDate());
        map.put("phone", userDetails.getPhone());
        map.put("skype", userDetails.getSkype());
        map.put("id", userDetails.getUser().getId());

        return map;
    }
}
