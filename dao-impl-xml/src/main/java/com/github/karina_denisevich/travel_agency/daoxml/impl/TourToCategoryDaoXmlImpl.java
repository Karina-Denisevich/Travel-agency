package com.github.karina_denisevich.travel_agency.daoxml.impl;

import com.github.karina_denisevich.travel_agency.daoapi.TourToCategoryDao;
import com.github.karina_denisevich.travel_agency.daoxml.model.TourToCategory;
import com.github.karina_denisevich.travel_agency.daoxml.util.XmlFileIOUtils;
import com.github.karina_denisevich.travel_agency.datamodel.Category;
import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class TourToCategoryDaoXmlImpl implements TourToCategoryDao {

    @Value("${basePath}")
    private String basePath;

    private XmlFileIOUtils<TourToCategory> xmlFileIOUtils;

    @PostConstruct
    private void initialize() throws IOException {
        String fileName = basePath.concat("\\").concat("tour_to_category").concat(".xml");
        xmlFileIOUtils = new XmlFileIOUtils<>(fileName, TourToCategory.class);
    }

    @Override
    public void insertTourWithCategories(Tour tour) {
        List<TourToCategory> entityList = xmlFileIOUtils.readCollection();

        entityList.addAll(getListForTour(tour));

        xmlFileIOUtils.writeCollection(entityList);
    }

    private List<TourToCategory> getListForTour(Tour tour) {
        List<TourToCategory> tourToCategoryList = new ArrayList<>();

        tour.getCategoryList().forEach(category -> {
            TourToCategory tourToCategory = new TourToCategory();
            tourToCategory.setTour(tour);
            tourToCategory.setCategory(category);
            tourToCategoryList.add(tourToCategory);
        });
        return tourToCategoryList;
    }

    @Override
    public void deleteByTourId(Long id) {
        List<TourToCategory> tourToCategoryList = xmlFileIOUtils.readCollection();

        Iterator iterator = tourToCategoryList.iterator();
        while (iterator.hasNext()) {
            TourToCategory tourToCategory = (TourToCategory) iterator.next();
            if (tourToCategory.getTour().getId().equals(id)) {
                iterator.remove();
            }
        }
        xmlFileIOUtils.writeCollection(tourToCategoryList);
    }

    @Override
    public void deleteByCategoryId(Long id) {
        List<TourToCategory> tourToCategoryList = xmlFileIOUtils.readCollection();

        Iterator iterator = tourToCategoryList.iterator();
        while (iterator.hasNext()) {
            TourToCategory tourToCategory = (TourToCategory) iterator.next();
            if (tourToCategory.getCategory().getId().equals(id)) {
                iterator.remove();
            }
        }
        xmlFileIOUtils.writeCollection(tourToCategoryList);
    }
}
