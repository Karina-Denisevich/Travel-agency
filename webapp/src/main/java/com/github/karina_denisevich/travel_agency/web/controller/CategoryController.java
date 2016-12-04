package com.github.karina_denisevich.travel_agency.web.controller;

import com.github.karina_denisevich.travel_agency.datamodel.Category;
import com.github.karina_denisevich.travel_agency.services.CategoryService;
import com.github.karina_denisevich.travel_agency.web.dto.CategoryDto;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping("/categories")
@SuppressWarnings("unchecked")
public class CategoryController extends AbstractController<Category, CategoryDto, Long> {

    @Inject
    private CategoryService categoryService;

    @Inject
    private ConversionServiceFactoryBean conversionService;

    @RequestMapping(value = "/search", method = RequestMethod.GET, params = "categoryType")
    public ResponseEntity<CategoryDto> getByType(@RequestParam String categoryType) {
        Category.CategoryEnum categoryEnum = Category.CategoryEnum.valueOf(categoryType);

        return new ResponseEntity<>(conversionService.getObject()
                .convert(categoryService.getByType(categoryEnum), CategoryDto.class), HttpStatus.OK);
    }
}
