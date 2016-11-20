package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.Category;
import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import org.junit.*;
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

    private Long id;
    private String title;

    @Before
    public void insertTest() {
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
        tour.setPrice(800.0);
        tour.setTitle("Test tour");
        title = tour.getTitle();

        id = tourService.save(tour);
        Assert.assertNotNull(id);
    }

    @Test
    public void getByIdTest() {
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
    public void updateTest() {
        Tour tour = new Tour();
        Category category = new Category();
        category.setType(Category.CategoryEnum.SHOP_TOUR);
        Category category1 = new Category();
        category1.setType(Category.CategoryEnum.BEACH_TOUR);
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        categories.add(category1);

        tour.setId(id);
        tour.setCategoryList(categories);
        tour.setDescription("Updated11 tour");
        tour.setIsHot(false);
        tour.setPrice(800.0);
        tour.setTitle("Shop tour");

        tourService.save(tour);
        Assert.assertEquals("Shop tour", tourService.get(id).getTitle());
    }

    @Test
    public void getByTitleTest() {
        List<Tour> tourList = tourService.getByTitle(title);
        Assert.assertEquals(title, tourList.get(0).getTitle());
    }

    @After
    public void deleteTest() {
        tourService.delete(id);
    }
}
