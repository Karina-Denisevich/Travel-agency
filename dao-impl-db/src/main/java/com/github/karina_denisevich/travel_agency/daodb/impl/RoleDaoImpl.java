package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.annotation.DbTableAnalyzer;
import com.github.karina_denisevich.travel_agency.daodb.RoleDao;
import com.github.karina_denisevich.travel_agency.daodb.unmapper.RoleUnmapper;
import com.github.karina_denisevich.travel_agency.datamodel.Role;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role, Long> implements RoleDao {

    @Inject
    JdbcTemplate jdbcTemplate;

    private final String tableName;

    public RoleDaoImpl() {
        super(new RoleUnmapper());
        this.tableName = new DbTableAnalyzer().getDbTableName(Role.class);
    }

    @Override
    public Role getByType(Role.RoleEnum roleEnum) {
        String sql = "SELECT * FROM " + tableName + " WHERE type = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{roleEnum.toString()},
                new BeanPropertyRowMapper<>(Role.class));
    }
}
