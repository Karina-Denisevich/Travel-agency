package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daodb.CategoryDao;
import com.github.karina_denisevich.travel_agency.daodb.Tour2CategoryDao;
import com.github.karina_denisevich.travel_agency.daodb.TourDao;
import com.github.karina_denisevich.travel_agency.datamodel.Category;
import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import com.github.karina_denisevich.travel_agency.services.TourService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class TourServiceImpl implements TourService {

    @Inject
    TourDao tourDao;

    @Inject
    CategoryDao categoryDao;

    @Inject
    Tour2CategoryDao tour2CategoryDao;

    @Override
    public Long save(Tour tour) {

        List<Category> categories = tour.getCategoryList();
        categories.stream().filter(category -> category.getId() == null).forEach(category ->
                categories.set(categories.indexOf(category), categoryDao.getByType(category.getType())));

        if (tour.getId() == null) {
            Long id = tourDao.insert(tour);
            tour.setId(id);
            tour2CategoryDao.insertBatch(tour);
            return id;
        } else {
            tourDao.update(tour);
            return tour.getId();
        }
    }

    @Override
    public void saveAll(List<Tour> tours) {

    }

    @Override
    public Tour get(Long id) {
        return null;
    }

    @Override
    public List<Tour> getAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
