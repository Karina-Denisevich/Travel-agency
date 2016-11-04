package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.Category;
import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class TourTest {

    @Inject
    TourService tourService;

    @Test
    public void getByIdTest() {
        Long id = 1L;
        Tour tour = tourService.get(id);

        Assert.assertNotNull("tour for id=" + id + " should not be null", tour);
        Assert.assertEquals(id, tour.getId());
    }

    @Test
    public void getAllTest() {
        List<Tour> tourList = tourService.getAll();
        Assert.assertNotNull("tours' list should not be null", tourList);
    }

    @Test
    public void insertTest(){
        Tour tour = new Tour();
        Category category = new Category();
        category.setType(Category.CategoryEnum.OTHER_TOUR);
        Category category1 = new Category();
        category1.setType(Category.CategoryEnum.BUS_TOUR);
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        categories.add(category1);

        tour.setCategoryList(categories);
        tour.setDescription("Some tour");
        tour.setIsHot(false);
        tour.setPrice(800.0);
        tour.setTitle("Beach3 tour");

        Long pk = tourService.save(tour);
        Assert.assertNotNull(pk);
    }

    @Test
    public void updateTest(){
        Tour tour = new Tour();
        Category category = new Category();
        category.setType(Category.CategoryEnum.SHOP_TOUR);
        Category category1 = new Category();
        category1.setType(Category.CategoryEnum.OTHER_TOUR);
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        categories.add(category1);

        tour.setId(1L);
        tour.setCategoryList(categories);
        tour.setDescription("Updated tour");
        tour.setIsHot(false);
        tour.setPrice(800.0);
        tour.setTitle("Shop tour");

        Long pk = tourService.save(tour);
        Assert.assertNotNull(pk);
    }

    @Test
    public void deleteTest(){
        tourService.delete(2L);
    }
}
