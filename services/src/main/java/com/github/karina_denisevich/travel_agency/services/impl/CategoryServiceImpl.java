package com.github.karina_denisevich.travel_agency.services.impl;


import com.github.karina_denisevich.travel_agency.daodb.CategoryDao;
import com.github.karina_denisevich.travel_agency.daodb.Tour2CategoryDao;
import com.github.karina_denisevich.travel_agency.datamodel.Category;
import com.github.karina_denisevich.travel_agency.services.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Inject
    CategoryDao categoryDao;

    @Inject
    Tour2CategoryDao tour2CategoryDao;

    @Override
    public Long save(Category category) {
        if (category.getId() == null) {
            return categoryDao.insert(category);
        } else {
            categoryDao.update(category);
            return category.getId();
        }
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

    @Transactional
    @Override
    public void delete(Long id) {
        tour2CategoryDao.deleteByCategoryId(id);
        categoryDao.delete(id);
    }

    @Override
    public Category getByType(Category.CategoryEnum type) {
        return categoryDao.getByType(type);
    }
}
