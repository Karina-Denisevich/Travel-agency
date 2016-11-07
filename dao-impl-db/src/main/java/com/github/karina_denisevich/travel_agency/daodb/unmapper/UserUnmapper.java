package com.github.karina_denisevich.travel_agency.daodb.unmapper;

import com.github.karina_denisevich.travel_agency.datamodel.User;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserUnmapper implements RowUnmapper<User> {

    @Override
    public Map<String, Object> mapColumns(User user) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("email", user.getEmail());
        map.put("password", user.getPassword());
        map.put("role_id", user.getRole().getId());
        if (user.getId() != null) {
            map.put("id", user.getId());
        }
        return map;
    }
}
