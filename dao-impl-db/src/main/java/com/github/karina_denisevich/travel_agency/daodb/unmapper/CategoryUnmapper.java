package com.github.karina_denisevich.travel_agency.daodb.unmapper;

import com.github.karina_denisevich.travel_agency.datamodel.Category;

import java.util.Map;

public class CategoryUnmapper implements RowUnmapper<Category> {

    @Override
    public Map<String, Object> mapColumns(Category category) {
        return null;
    }
}
