package com.github.karina_denisevich.travel_agency.services.impl;


import com.github.karina_denisevich.travel_agency.daodb.util.CategoryDao;
import com.github.karina_denisevich.travel_agency.datamodel.Category;
import com.github.karina_denisevich.travel_agency.services.util.CategoryService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Inject
    CategoryDao categoryDao;

    @Override
    public void save(Category category) {

    }

    @Override
    public void saveAll(List<Category> categories) {

    }

    @Override
    public Category get(Long id) {
        return null;
    }

    @Override
    public List<Category> getAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
