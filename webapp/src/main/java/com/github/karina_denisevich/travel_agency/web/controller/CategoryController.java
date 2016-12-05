package com.github.karina_denisevich.travel_agency.web.controller;

import com.github.karina_denisevich.travel_agency.datamodel.Category;
import com.github.karina_denisevich.travel_agency.services.CategoryService;
import com.github.karina_denisevich.travel_agency.web.dto.CategoryDto;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
@SuppressWarnings("unchecked")
public class CategoryController extends AbstractController<Category, CategoryDto, Long> {

    @Inject
    private CategoryService categoryService;

    @Inject
    private ConversionServiceFactoryBean conversionService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDto>> getByType(@RequestParam(value = "categoryType", required = false) String categoryType) {
        List<Category> categoryList = new ArrayList<>();
        if (categoryType != null) {
            Category.CategoryEnum categoryEnum = Category.CategoryEnum.valueOf(categoryType);
            categoryList.add(categoryService.getByType(categoryEnum));
        } else {
            categoryList = new ArrayList<>(categoryService.getAll());
        }

        if (CollectionUtils.isEmpty(categoryList)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<CategoryDto> convertedList = (List<CategoryDto>) conversionService.getObject()
                .convert(categoryList, TypeDescriptor.valueOf(List.class),
                        TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(CategoryDto.class)));

        return new ResponseEntity<>(convertedList, HttpStatus.OK);
    }
}
