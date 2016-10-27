package com.github.karina_denisevich.travel_agency.daodb.unmapper;

import com.github.karina_denisevich.travel_agency.datamodel.Role;

import java.util.Map;

public class RoleUnmapper implements RowUnmapper<Role> {

    @Override
    public Map<String, Object> mapColumns(Role role) {
        return null;
    }
}
