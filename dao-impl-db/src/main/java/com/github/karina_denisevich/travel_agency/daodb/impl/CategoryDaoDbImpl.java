package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.daoapi.CategoryDao;
import com.github.karina_denisevich.travel_agency.daoapi.exception.EmptyResultException;
import com.github.karina_denisevich.travel_agency.daodb.unmapper.CategoryUnmapper;
import com.github.karina_denisevich.travel_agency.datamodel.Category;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class CategoryDaoDbImpl extends GenericDaoDbImpl<Category, Long> implements CategoryDao {

    @Inject
    private JdbcTemplate jdbcTemplate;

    public CategoryDaoDbImpl() {
        super(new CategoryUnmapper());
    }

    @Override
    public Category getByType(Category.CategoryEnum type) {
        String sql = "SELECT * FROM " + tableName + " WHERE type = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{type.toString()},
                    new BeanPropertyRowMapper<>(Category.class));
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyResultException("There is no entity with type = " + type);
        }
    }
}
