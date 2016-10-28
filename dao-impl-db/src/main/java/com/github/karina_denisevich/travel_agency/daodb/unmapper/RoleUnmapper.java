package com.github.karina_denisevich.travel_agency.daodb.unmapper;

import com.github.karina_denisevich.travel_agency.datamodel.Role;

import java.util.LinkedHashMap;
import java.util.Map;

public class RoleUnmapper implements RowUnmapper<Role> {

    @Override
    public Map<String, Object> mapColumns(Role role) {

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("type", role.getType().toString());
        if (role.getId() != null) {
            map.put("id", role.getId());
        }
        return map;
    }
}
