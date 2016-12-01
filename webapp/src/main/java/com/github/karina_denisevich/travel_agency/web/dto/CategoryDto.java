package com.github.karina_denisevich.travel_agency.web.dto;

import com.github.karina_denisevich.travel_agency.datamodel.Category;

public class CategoryDto extends AbstractDto{

    private Category.CategoryEnum type;

    public Category.CategoryEnum getType() {
        return type;
    }

    public void setType(Category.CategoryEnum type) {
        this.type = type;
    }
}
