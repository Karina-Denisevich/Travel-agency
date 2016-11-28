package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daoapi.CategoryDao;
import com.github.karina_denisevich.travel_agency.daoapi.TourToCategoryDao;
import com.github.karina_denisevich.travel_agency.datamodel.Category;
import com.github.karina_denisevich.travel_agency.services.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Inject
    private CategoryDao categoryDao;

    @Inject
    private TourToCategoryDao tourToCategoryDao;

    @Override
    public Long save(Category category) {
        if (category.getId() == null) {
            return categoryDao.insert(category);
        } else {
            categoryDao.update(category);
            return category.getId();
        }
    }

    @Transactional
    @Override
    public void saveAll(List<Category> categories) {
        categories.forEach(this::save);
    }

    @Override
    public Category get(Long id) {

        return categoryDao.get(id);
    }

    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        tourToCategoryDao.deleteByCategoryId(id);
        categoryDao.delete(id);
    }

    @Override
    public Category getByType(Category.CategoryEnum type) {
        return categoryDao.getByType(type);
    }
}
