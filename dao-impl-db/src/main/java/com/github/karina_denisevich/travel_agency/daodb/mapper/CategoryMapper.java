package com.github.karina_denisevich.travel_agency.daodb.mapper;

import com.github.karina_denisevich.travel_agency.datamodel.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category> {

    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong("id"));
        category.setType(Category.CategoryEnum.valueOf(rs.getString("type")));
        return category;
    }
}
