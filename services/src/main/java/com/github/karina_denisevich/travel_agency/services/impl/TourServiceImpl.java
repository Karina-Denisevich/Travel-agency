package com.github.karina_denisevich.travel_agency.services.impl;

import com.github.karina_denisevich.travel_agency.daoapi.TourDao;
import com.github.karina_denisevich.travel_agency.daoapi.TourToCategoryDao;
import com.github.karina_denisevich.travel_agency.datamodel.Category;
import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import com.github.karina_denisevich.travel_agency.services.BookingService;
import com.github.karina_denisevich.travel_agency.services.CategoryService;
import com.github.karina_denisevich.travel_agency.services.TourService;
import com.github.karina_denisevich.travel_agency.services.locale.CustomLocale;
import com.github.karina_denisevich.travel_agency.services.util.PropertyFileUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.apache.commons.lang3.Validate;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class TourServiceImpl implements TourService {

    private static final String EN_PROPERTIES_FILE = "services\\src\\main\\resources\\tours.properties";
    private static final String RU_PROPERTIES_FILE = "services\\src\\main\\resources\\tours_ru.properties";

    @Inject
    private TourDao tourDao;

    @Inject
    private CategoryService categoryService;

    @Inject
    private BookingService bookingService;

    @Inject
    private TourToCategoryDao tourToCategoryDao;

    @Inject
    private MessageSource messageSource;

    @Inject
    private CustomLocale customLocale;

    @Transactional
    @Override
    public Long save(Tour tour) {
        beforeSave(tour);
        if (tour.getId() == null) {
            Long id = tourDao.insert(tour);
            tour.setId(id);
            tourToCategoryDao.insertTourWithCategories(tour);
            return id;
        } else {
            tourToCategoryDao.deleteByTourId(tour.getId());
            tourToCategoryDao.insertTourWithCategories(tour);
            if (tourDao.update(tour) == 0) {
                return null;
            }
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
            categories.stream().filter(category -> category.getId() == null)
                    .forEach(category -> categories.set(categories.indexOf(category),
                            categoryService.getByType(category.getType())));
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


        // try {
//            String result = URLEncoder.encode("Hello my dear students", "UTF-8");
//            URL url = new URL("http://translate.googleapis.com/translate_a/single?client=gtx&sl=" + "en" + "&tl="
//                    + "ru" + "&dt=t&q=" + result + "&ie=UTF-8&oe=UTF-8");
//
//            URLConnection uc = url.openConnection();
//            uc.setRequestProperty("User-Agent", "Mozilla/5.0");
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
//            result = br.readLine();


//            URL url = new URL("https://translate.yandex.net/api/v1.5/tr.json/translate?" +
//                    "key=" +
//                    "&text=Hello world" +
//                    "&lang=ru");
//            URLConnection uc = url.openConnection();
//            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
//            String result = br.readLine();
//            System.out.println("*******  " + result);
//
//            JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
//            result = jsonObject.get("text").getAsString();
//
//            System.out.println("______________" + result);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        Tour tour = tourDao.get(id);
        tour.setTitle(getField(id, tour.getTitle(), customLocale.getLanguage()));
        return tour;
    }

    @Override
    public List<Tour> getAll() {
        List<Tour> tourList = tourDao.getAll();
        tourList.forEach(tour -> tour.setTitle(getField(tour.getId(), tour.getTitle(), customLocale.getLanguage())));
        return tourList;
    }

    @Transactional
    @Override
    public int delete(Long id) {
        bookingService.deleteByTourId(id);
        tourToCategoryDao.deleteByTourId(id);

        int deleted = tourDao.delete(id);
        new PropertyFileUtil().deleteByKey(id + "_", EN_PROPERTIES_FILE, RU_PROPERTIES_FILE);
        return deleted;
    }

    @Override
    public List<Tour> getByTitle(String title) {
        String titleKey = new PropertyFileUtil().getKeyByValue(title, EN_PROPERTIES_FILE, RU_PROPERTIES_FILE);

        List<Tour> tourList = tourDao.getByTitle(titleKey.substring(titleKey.indexOf('_') + 1));
        tourList.forEach(t -> t.setTitle(title));
        return tourList;
    }

    private String getField(Long id, String key, String language) {
        return messageSource.getMessage(id.toString().concat("_").concat(key), null,
                new Locale(language));
    }
}
