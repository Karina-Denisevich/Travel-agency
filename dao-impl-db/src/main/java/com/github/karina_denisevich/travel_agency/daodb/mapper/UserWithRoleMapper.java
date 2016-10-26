package com.github.karina_denisevich.travel_agency.daodb.mapper;


import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserWithRoleMapper implements RowMapper<User> {

    private final RoleMapper roleMapper;

    public UserWithRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User entity = new User();
        entity.setId(rs.getLong("id"));
        entity.setEmail(rs.getString("email"));
        entity.setPassword(rs.getString("password"));

        Role role = this.roleMapper.mapRow(rs, rowNum);
        entity.setRole(role);

        return entity;
    }
}
