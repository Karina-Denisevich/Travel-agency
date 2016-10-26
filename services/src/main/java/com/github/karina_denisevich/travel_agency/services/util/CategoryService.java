package com.github.karina_denisevich.travel_agency.services.util;

import com.github.karina_denisevich.travel_agency.datamodel.Category;

import java.util.List;

public interface CategoryService {

    void save(Category category);

    void saveAll(List<Category> categories);

    Category get(Long id);

    List<Category> getAll();

    void delete(Long id);
}
