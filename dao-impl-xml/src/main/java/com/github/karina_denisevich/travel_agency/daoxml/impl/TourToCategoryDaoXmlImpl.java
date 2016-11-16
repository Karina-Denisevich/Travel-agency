package com.github.karina_denisevich.travel_agency.daoxml.impl;

import com.github.karina_denisevich.travel_agency.daoapi.TourToCategoryDao;
import com.github.karina_denisevich.travel_agency.daoxml.impl.model.TourToCategory;
import com.github.karina_denisevich.travel_agency.datamodel.Category;
import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import com.thoughtworks.xstream.XStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class TourToCategoryDaoXmlImpl implements TourToCategoryDao {

    private final String rootName = "tour_to_category";
    private XStream xstreamTour;
    private File tourToCategory;

    @Value("${basePath}")
    private String basePath;

    @PostConstruct
    private void initialize() throws IOException {
        xstreamTour = new XStream();
        xstreamTour.alias(rootName, TourToCategory.class);

        tourToCategory = new File(basePath + "\\" + rootName + ".xml");
        tourToCategory.getParentFile().mkdirs();
        if (!tourToCategory.exists()) {
            xstreamTour.toXML(new ArrayList<>(), new FileOutputStream(
                    tourToCategory));
        }
    }

    @Override
    public void insertTourWithCategories(Tour tour) {
        List<TourToCategory> entityList = readCollection();

        entityList.addAll(getListForTour(tour));

        writeCollection(entityList);
    }

    private List<TourToCategory> getListForTour(Tour tour) {
        List<TourToCategory> tourToCategoryList = new ArrayList<>();

        for (Category category : tour.getCategoryList()) {
            TourToCategory tourToCategory = new TourToCategory();
            tourToCategory.setTour(tour);
            tourToCategory.setCategory(category);
            tourToCategoryList.add(tourToCategory);
        }

        return tourToCategoryList;
    }

    @Override
    public void deleteByTourId(Long id) {
        List<TourToCategory> tourToCategoryList = readCollection();

        Iterator iterator = tourToCategoryList.iterator();
        while (iterator.hasNext()) {
            TourToCategory tourToCategory = (TourToCategory) iterator.next();
            if (tourToCategory.getTour().getId().equals(id)) {
                iterator.remove();
            }
        }
        writeCollection(tourToCategoryList);
    }

    @Override
    public void deleteByCategoryId(Long id) {
        List<TourToCategory> tourToCategoryList = readCollection();

        Iterator iterator = tourToCategoryList.iterator();
        while (iterator.hasNext()) {
            TourToCategory tourToCategory = (TourToCategory) iterator.next();
            if (tourToCategory.getCategory().getId().equals(id)) {
                iterator.remove();
            }
        }
        writeCollection(tourToCategoryList);
    }

    @SuppressWarnings("unchecked")
    private List<TourToCategory> readCollection() {

        return (List<TourToCategory>) xstreamTour.fromXML(tourToCategory);
    }

    private void writeCollection(List<TourToCategory> newList) {
        try {
            xstreamTour.toXML(newList, new FileOutputStream(tourToCategory));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);// TODO custom exception
        }
    }
}
