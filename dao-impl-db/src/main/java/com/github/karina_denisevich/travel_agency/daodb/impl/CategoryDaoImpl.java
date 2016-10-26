package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.daodb.CategoryDao;
import com.github.karina_denisevich.travel_agency.datamodel.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    @Override
    public Category get(Long id) {
        return null;
    }

    @Override
    public Long insert(Category entity) {
        return null;
    }

    @Override
    public void update(Category entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Category> getAll() {
        return null;
    }

    @Override
    public void insertBatch(List<Category> categories) {

    }
}
