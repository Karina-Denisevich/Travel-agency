package com.github.karina_denisevich.travel_agency.daodb.mapper;

import com.github.karina_denisevich.travel_agency.daodb.mapper.util.MapperUtil;
import com.github.karina_denisevich.travel_agency.datamodel.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet rs, int i) throws SQLException {
        Role role = new Role();
        role.setId(new MapperUtil().getId(rs, "role"));
        role.setType(Role.RoleEnum.valueOf(rs.getString("type")));

        return role;
    }
}
