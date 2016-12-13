package com.github.karina_denisevich.travel_agency.daodb.mapper;

import com.github.karina_denisevich.travel_agency.daodb.mapper.util.MapperUtil;
import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserWithRoleMapper implements RowMapper<User> {

    private final RoleMapper roleMapper;

    public UserWithRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(new MapperUtil().getId(rs, "user"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));

        Role role = this.roleMapper.mapRow(rs, rowNum);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        role.setUsers(userList);
        user.setRole(role);

        return user;
    }
}
