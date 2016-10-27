package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.daodb.RoleDao;
import com.github.karina_denisevich.travel_agency.daodb.unmapper.RoleUnmapper;
import com.github.karina_denisevich.travel_agency.datamodel.Role;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role, Long> implements RoleDao {

    @Inject
    JdbcTemplate jdbcTemplate;

    public RoleDaoImpl(){
        super(new RoleUnmapper());
    }

    @Override
    public Role getByType(Role.RoleEnum roleEnum) {
        String sql = "SELECT * FROM role WHERE type = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{roleEnum.toString()},
                new BeanPropertyRowMapper<>(Role.class));
    }

    @Override
    public void insertBatch(List<Role> roles) {}
}
