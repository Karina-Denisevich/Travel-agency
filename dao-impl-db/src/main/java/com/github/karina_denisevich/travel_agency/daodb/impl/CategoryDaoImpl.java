package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.daodb.CategoryDao;
import com.github.karina_denisevich.travel_agency.daodb.mapper.CategoryMapper;
import com.github.karina_denisevich.travel_agency.daodb.unmapper.CategoryUnmapper;
import com.github.karina_denisevich.travel_agency.datamodel.Category;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class CategoryDaoImpl extends GenericDaoImpl<Category, Long> implements CategoryDao {

    @Inject
    JdbcTemplate jdbcTemplate;

    public CategoryDaoImpl() {
        super(new CategoryUnmapper());
    }

    @Override
    public void insertBatch(List<Category> categories) {

    }

    @Override
    public Category getByType(Category.CategoryEnum type) {
        final String sql = "SELECT * FROM category WHERE type = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{type.toString()},
                new BeanPropertyRowMapper<>(Category.class));
    }
}
