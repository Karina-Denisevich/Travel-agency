package com.github.karina_denisevich.travel_agency.daoapi;

import com.github.karina_denisevich.travel_agency.datamodel.Category;

public interface CategoryDao extends GenericDao<Category, Long> {

    Category getByType(Category.CategoryEnum type);
}
