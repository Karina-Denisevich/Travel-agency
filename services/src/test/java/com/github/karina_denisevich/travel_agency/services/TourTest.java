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
    public void insertTest(){
        Tour tour = new Tour();
        Category category = new Category();
        category.setType(Category.CategoryEnum.OTHER_TOUR);
        Category category1 = new Category();
        category1.setType(Category.CategoryEnum.BEACH_TOUR);
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        categories.add(category1);

        tour.setCategoryList(categories);
        tour.setDescription("Some tour");
        tour.setIsHot(false);
        tour.setPrice(1000.0);
        tour.setTitle("Beach tour");

        Long pk = tourService.save(tour);
        Assert.assertNotNull(pk);
    }
}
