package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daoapi.TourDao;
import com.github.karina_denisevich.travel_agency.daoapi.TourToCategoryDao;
import com.github.karina_denisevich.travel_agency.datamodel.Category;
import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import com.github.karina_denisevich.travel_agency.services.BookingService;
import com.github.karina_denisevich.travel_agency.services.CategoryService;
import com.github.karina_denisevich.travel_agency.services.TourService;
import com.github.karina_denisevich.travel_agency.services.locale.CustomLocale;
import com.github.karina_denisevich.travel_agency.services.locale.util.PropertyFileUtil;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class TourServiceImpl implements TourService {

    private static final String EN_PROPERTIES_FILE = "services\\src\\main\\resources\\tours_en.properties";
    private static final String RU_PROPERTIES_FILE = "services\\src\\main\\resources\\tours_ru.properties";
    private static final String DE_PROPERTIES_FILE = "services\\src\\main\\resources\\tours_de.properties";

    @Inject
    private TourDao tourDao;

    @Inject
    private CategoryService categoryService;

    @Inject
    private BookingService bookingService;

    @Inject
    private TourToCategoryDao tourToCategoryDao;

    @Inject
    private CustomLocale customLocale;

    @Transactional
    @Override
    public Long save(Tour tour) {
        beforeSave(tour);
        Long id;
        if (tour.getId() == null) {
            id = tourDao.insert(tour);
            tour.setId(id);
            tourToCategoryDao.insertTourWithCategories(tour);
        } else if (tourDao.update(tour) != 0) {
            tourToCategoryDao.deleteByTourId(tour.getId());
            tourToCategoryDao.insertTourWithCategories(tour);
            new PropertyFileUtil().deleteByKey(tour.getId() + ".", EN_PROPERTIES_FILE,
                    RU_PROPERTIES_FILE, DE_PROPERTIES_FILE);
            id = tour.getId();
        } else {
            return null;
        }
        afterSave(id, tour.getTitle());
        return id;
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
            categories.stream().filter(category -> category.getId() == null)
                    .forEach(category -> categories.set(categories.indexOf(category),
                            categoryService.getByType(category.getType())));
        } else {
            List<Category> categoryOther = new ArrayList<>();
            categoryOther.add(categoryService.getByType(Category.CategoryEnum.OTHER_TOUR));
            tour.setCategoryList(categoryOther);
        }
    }

    private void afterSave(Long id, String title) {
        new PropertyFileUtil().write(id + "." + title, title, "ru", RU_PROPERTIES_FILE);
        new PropertyFileUtil().write(id + "." + title, title, "en", EN_PROPERTIES_FILE);
        new PropertyFileUtil().write(id + "." + title, title, "de", DE_PROPERTIES_FILE);
    }

    @Transactional
    @Override
    public void saveAll(List<Tour> tours) {
        tours.forEach(this::save);
    }

    @Override
    public Tour get(Long id) {
        Tour tour = tourDao.get(id);
        tour.setTitle(new PropertyFileUtil().getValueByLocale(getKey(id, tour.getTitle()), "tours"
                , customLocale.getLanguage()));
        return tour;
    }

    @Override
    public List<Tour> getAll() {
        List<Tour> tourList = tourDao.getAll();
        PropertyFileUtil prFileUtil = new PropertyFileUtil();
        tourList.forEach(tour -> tour.setTitle(prFileUtil.getValueByLocale(getKey(tour.getId(),
                tour.getTitle()), "tours", customLocale.getLanguage())));
        return tourList;
    }

    @Transactional
    @Override
    public int delete(Long id) {
        bookingService.deleteByTourId(id);
        tourToCategoryDao.deleteByTourId(id);

        int deletedCount = tourDao.delete(id);
        new PropertyFileUtil().deleteByKey(id.toString(), EN_PROPERTIES_FILE, RU_PROPERTIES_FILE, DE_PROPERTIES_FILE);
        return deletedCount;
    }

    @Override
    public List<Tour> getByTitle(String title) {
        String titleKey = new PropertyFileUtil().getKeyByValue(title, EN_PROPERTIES_FILE, RU_PROPERTIES_FILE, DE_PROPERTIES_FILE);

        List<Tour> tourList = tourDao.getByTitle(titleKey.substring(titleKey.indexOf('.') + 1));
        tourList.forEach(t -> t.setTitle(title));
        return tourList;
    }

    private String getKey(Long id, String key) {
        return id.toString().concat(".").concat(key);
    }
}
