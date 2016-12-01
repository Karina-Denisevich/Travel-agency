package com.github.karina_denisevich.travel_agency.web.converter.dto_to_entity;

import com.github.karina_denisevich.travel_agency.datamodel.Category;
import com.github.karina_denisevich.travel_agency.web.dto.CategoryDto;
import org.springframework.core.convert.converter.Converter;

public class DtoToCategory implements Converter<CategoryDto, Category> {

    @Override
    public Category convert(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setType(categoryDto.getType());

        return category;
    }
}
