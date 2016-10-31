package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.Category;
import com.github.karina_denisevich.travel_agency.datamodel.Role;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class CategoryTest {

    @Inject
    CategoryService categoryService;

    @Test
    public void getByIdTest() {
        Long id = 2L;
        Category category = categoryService.get(id);

        Assert.assertNotNull("category for id=" + id + " should not be null", category);
        Assert.assertEquals(id, category.getId());
    }

    @Test
    public void getAllTest() {
        List<Category> categoryList = categoryService.getAll();
        Assert.assertNotNull("categories' list should not be null", categoryList);
    }

    @Test
    @Ignore
    public void insertTest() {
        Category category1 = new Category();
        category1.setType(Category.CategoryEnum.SHOP_TOUR);
        Category category2 = new Category();
        category2.setType(Category.CategoryEnum.BUS_TOUR);

        Long pk1 = categoryService.save(category1);
        Long pk2 = categoryService.save(category2);

        Assert.assertNotNull(pk1);
        Assert.assertNotNull(pk2);
    }

    @Test
    public void updateTest() {
        Category category = new Category();
        category.setId(4L);
        category.setType(Category.CategoryEnum.SAFARI_TOUR);
        Long pk = categoryService.save(category);

        Assert.assertNotNull(pk);
    }

    @Test
    @Ignore
    public void getByType() {
        Category category = categoryService.getByType(Category.CategoryEnum.OTHER_TOUR);
        System.out.println(category);
        Assert.assertEquals(Category.CategoryEnum.OTHER_TOUR, category.getType());
    }

    @Test
    public void deleteTest() {
        categoryService.delete(1L);
    }
}
