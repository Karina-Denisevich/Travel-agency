package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.Category;

import java.util.List;

public interface CategoryService extends AbstractService<Category, Long> {

    Category getByType(Category.CategoryEnum type);
}
