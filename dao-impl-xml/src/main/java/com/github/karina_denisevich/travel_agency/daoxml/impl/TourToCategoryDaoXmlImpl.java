package com.github.karina_denisevich.travel_agency.daoxml.impl;

import com.github.karina_denisevich.travel_agency.daoapi.TourToCategoryDao;
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
import java.util.List;

@Repository
public class TourToCategoryDaoXmlImpl implements TourToCategoryDao {

    private final String rootNameTour = "tour";
    private final String categoryListName = "categoryList";
    private XStream xstreamTour;
    private File fileTour;

    @Value("${basePath}")
    private String basePath;

    @PostConstruct
    private void initialize() throws IOException {
        xstreamTour = new XStream();
        xstreamTour.alias(rootNameTour, Tour.class);
        xstreamTour.alias(categoryListName, Category.class);

        fileTour = new File(basePath + "\\" + rootNameTour + ".xml");
        fileTour.getParentFile().mkdirs();
        if (!fileTour.exists()) {
            xstreamTour.toXML(new ArrayList<>(), new FileOutputStream(
                    fileTour));
        }
    }

    @Override
    public void insertTourWithCategories(Tour tour) {
        List<Tour> tourList = readCollection();

        for (Tour tour1 : tourList) {
            if (tour1.getId().equals(tour.getId())) {
                xstreamTour.addImplicitCollection(Category.class, "categoryList", Tour.class);
                break;
            }
        }
        writeCollection(tourList);
    }

    @Override
    public void deleteByTourId(Long id) {

    }

    @Override
    public void deleteByCategoryId(Long id) {

    }

    private List<Tour> readCollection() {

        return (List<Tour>) xstreamTour.fromXML(fileTour);
    }

    private void writeCollection(List<Tour> newList) {
        try {
            xstreamTour.toXML(newList, new FileOutputStream(fileTour));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);// TODO custom exception
        }
    }

    private long getNextId(List<Tour> tourList) {
        return tourList.isEmpty() ? 1L : tourList.get(
                tourList.size() - 1).getId() + 1;
    }
}
