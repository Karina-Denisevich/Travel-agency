package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.annotation.DbTableAnalyzer;
import com.github.karina_denisevich.travel_agency.daodb.CategoryDao;
import com.github.karina_denisevich.travel_agency.daodb.unmapper.CategoryUnmapper;
import com.github.karina_denisevich.travel_agency.datamodel.Category;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class CategoryDaoImpl extends GenericDaoImpl<Category, Long> implements CategoryDao {

    @Inject
    JdbcTemplate jdbcTemplate;

    private final String tableName;

    public CategoryDaoImpl() {
        super(new CategoryUnmapper());
        this.tableName = new DbTableAnalyzer().getDbTableName(Category.class);
    }

    @Override
    public Category getByType(Category.CategoryEnum type) {
        final String sql = "SELECT * FROM " + tableName + " WHERE type = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{type.toString()},
                new BeanPropertyRowMapper<>(Category.class));
    }
}
