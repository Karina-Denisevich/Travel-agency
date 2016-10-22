package com.github.karina_denisevich.travel_agency.daodb;

import com.github.karina_denisevich.travel_agency.datamodel.Category;

import java.util.List;

public interface CategoryDao {

    Category get(Long id);

    void insert(Category entity);

    void update(Category entity);

    void delete(Long id);

    List<Category> getAll();
}
