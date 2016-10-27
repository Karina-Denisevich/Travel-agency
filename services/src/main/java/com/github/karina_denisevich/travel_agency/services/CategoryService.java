package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.Category;

import java.util.List;

public interface CategoryService {

    Long save(Category category);

    void saveAll(List<Category> categories);

    Category get(Long id);

    List<Category> getAll();

    void delete(Long id);

    Category getByType(Category.CategoryEnum type);
}
