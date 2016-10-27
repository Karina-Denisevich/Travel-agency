package com.github.karina_denisevich.travel_agency.daodb.unmapper;

import com.github.karina_denisevich.travel_agency.datamodel.UserDetails;

import java.util.Map;

public class UserDetailsUnmapper implements RowUnmapper<UserDetails> {

    @Override
    public Map<String, Object> mapColumns(UserDetails userDetails) {
        return null;
    }
}
