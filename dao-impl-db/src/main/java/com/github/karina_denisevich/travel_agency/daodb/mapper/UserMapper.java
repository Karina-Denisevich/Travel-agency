package com.github.karina_denisevich.travel_agency.daodb.mapper;


import com.github.karina_denisevich.travel_agency.datamodel.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String email = rs.getString("email");
        String password = rs.getString("password");
        Long roleId = rs.getLong("role_id");

        User entity = new User();
        entity.setId(id);
        entity.setEmail(email);
        entity.setPassword(password);
        entity.setRoleId(roleId);
        return entity;
    }
}
