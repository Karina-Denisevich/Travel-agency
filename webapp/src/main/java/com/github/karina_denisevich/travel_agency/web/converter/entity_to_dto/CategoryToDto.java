package com.github.karina_denisevich.travel_agency.web.converter.entity_to_dto;

import com.github.karina_denisevich.travel_agency.datamodel.Category;
import com.github.karina_denisevich.travel_agency.web.dto.CategoryDto;
import org.springframework.core.convert.converter.Converter;

public class CategoryToDto implements Converter<Category, CategoryDto> {

    @Override
    public CategoryDto convert(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setType(category.getType());

        return categoryDto;
    }
}
