package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.daoapi.RoleDao;
import com.github.karina_denisevich.travel_agency.daoapi.exception.EmptyResultException;
import com.github.karina_denisevich.travel_agency.daodb.unmapper.RoleUnmapper;
import com.github.karina_denisevich.travel_agency.datamodel.Role;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class RoleDaoDbImpl extends GenericDaoDbImpl<Role, Long> implements RoleDao {

    @Inject
    JdbcTemplate jdbcTemplate;


    public RoleDaoDbImpl() {
        super(new RoleUnmapper());
    }

    @Override
    public Role getByType(Role.RoleEnum roleEnum) {
        String sql = "SELECT * FROM " + tableName + " WHERE type = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{roleEnum.toString()},
                    new BeanPropertyRowMapper<>(Role.class));
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyResultException("There is no entity with type = " + roleEnum);
        }
    }
}
