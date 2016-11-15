package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daoapi.TourToCategoryDao;
import com.github.karina_denisevich.travel_agency.daoapi.TourDao;
import com.github.karina_denisevich.travel_agency.datamodel.Category;
import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import com.github.karina_denisevich.travel_agency.services.BookingService;
import com.github.karina_denisevich.travel_agency.services.CategoryService;
import com.github.karina_denisevich.travel_agency.services.TourService;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class TourServiceImpl implements TourService {

    @Inject
    TourDao tourDao;

    @Inject
    CategoryService categoryService;

    @Inject
    BookingService bookingService;

    @Inject
    TourToCategoryDao tourToCategoryDao;

    @Transactional
    @Override
    public Long save(Tour tour) {
       // beforeSave(tour);
        if (tour.getId() == null) {
            Long id = tourDao.insert(tour);
            tour.setId(id);
            //tourToCategoryDao.insertTourWithCategories(tour);
            return id;
        } else {
            tourToCategoryDao.deleteByTourId(tour.getId());
            tourToCategoryDao.insertTourWithCategories(tour);
            tourDao.update(tour);
            return tour.getId();
        }
    }

    private void beforeSave(Tour tour) {
        Validate.notEmpty(tour.getTitle(), "Title should not be empty.");
        Validate.notNull(tour.getPrice(), "Price should not be null.");
        if (tour.getPrice() < 0.0) {
            throw new IllegalArgumentException("Price should be greater than zero.");
        }
        if (tour.getIsHot() == null) {
            tour.setIsHot(false);
        }

        List<Category> categories = tour.getCategoryList();
        if (categories != null) {
            categories.stream().filter(category -> category.getId() == null).forEach(category ->
                    categories.set(categories.indexOf(category), categoryService.getByType(category.getType())));
        } else {
            List<Category> categoryOther = new ArrayList<>();
            categoryOther.add(categoryService.getByType(Category.CategoryEnum.OTHER_TOUR));
            tour.setCategoryList(categoryOther);
        }
    }

    @Transactional
    @Override
    public void saveAll(List<Tour> tours) {
        tours.forEach(this::save);
    }

    @Override
    public Tour get(Long id) {
        return tourDao.get(id);
    }

    @Override
    public List<Tour> getAll() {
        return tourDao.getAll();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        bookingService.deleteByTourId(id);
        tourToCategoryDao.deleteByTourId(id);
        tourDao.delete(id);
    }

    @Override
    public List<Tour> getByTitle(String title) {
        return tourDao.getByTitle(title);
    }
}
