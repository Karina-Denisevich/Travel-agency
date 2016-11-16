package com.github.karina_denisevich.travel_agency.daoxml.impl;

import com.github.karina_denisevich.travel_agency.daoapi.CategoryDao;
import com.github.karina_denisevich.travel_agency.datamodel.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoXmlImpl extends GenericDaoXmlImpl<Category, Long> implements CategoryDao {

    @Override
    public Category getByType(Category.CategoryEnum type) {
        List<Category> categoryList = readCollection();

        for (Category category : categoryList) {
            if (category.getType().equals(type)) {
                return category;
            }
        }
        return null;
    }
}
