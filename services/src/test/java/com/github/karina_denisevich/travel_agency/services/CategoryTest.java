package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.Category;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class CategoryTest {

    @Inject
    CategoryService categoryService;

    @Test
    @Ignore
    public void insertTest(){
        Category category1 = new Category();
        category1.setType(Category.CategoryEnum.BEACH_TOUR);
        Category category2 = new Category();
        category2.setType(Category.CategoryEnum.OTHER_TOUR);

        categoryService.save(category1);
        categoryService.save(category2);
    }

    @Test
    @Ignore
    public void getByType(){
        Category category = categoryService.getByType(Category.CategoryEnum.OTHER_TOUR);
        System.out.println(category);
        Assert.assertEquals(Category.CategoryEnum.OTHER_TOUR, category.getType());
    }
}
