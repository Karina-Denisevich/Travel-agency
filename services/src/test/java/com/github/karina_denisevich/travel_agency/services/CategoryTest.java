package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.Category;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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

    private Long id;

    //@Before
    @Test
    public void insertTest() {
        Category category = new Category();
        category.setType(Category.CategoryEnum.BEACH_TOUR);

        id = categoryService.save(category);

        Assert.assertNotNull(id);
    }

    @Test
    public void getByIdTest() {
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
    public void updateTest() {
        Category category = new Category();
        category.setId(id);
        category.setType(Category.CategoryEnum.RAIL_TOUR);
        Long pk = categoryService.save(category);

        Assert.assertEquals(id, pk);
    }

    @Test
    public void getByType() {
        Category category = categoryService.getByType(Category.CategoryEnum.ESCOURTED_TOUR);
        System.out.println(category);
        Assert.assertEquals(Category.CategoryEnum.ESCOURTED_TOUR, category.getType());
    }

   // @After
    public void deleteTest() {
        categoryService.delete(id);
    }
}
