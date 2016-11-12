package com.github.karina_denisevich.travel_agency.daoxml.impl;

import com.github.karina_denisevich.travel_agency.annotation.DbTableAnalyzer;
import com.github.karina_denisevich.travel_agency.daoapi.CategoryDao;
import com.github.karina_denisevich.travel_agency.datamodel.Booking;
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
public class CategoryDaoXmlImpl implements CategoryDao {

    private final String rootName;
    private XStream xstream;
    private File file;

    @Value("${basePath}")
    private String basePath;

    @PostConstruct
    private void initialize() throws IOException {
        // TODO move to the parent class
        // TODO refactoring: use classname instead of hardcoded filename
        xstream = new XStream();
        xstream.alias(rootName, Category.class);

        file = new File(basePath + "\\" + rootName + ".xml");
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            xstream.toXML(new ArrayList<>(), new FileOutputStream(
                    file));
        }
    }

    public CategoryDaoXmlImpl() {
        this.rootName = new DbTableAnalyzer().getDbTableName(Category.class);
    }

    @Override
    public Category get(Long id) {
        return null;
    }

    @Override
    public Long insert(Category entity) {
        List<Category> categoryList = readCollection();
        Long id = getNextId(categoryList);

        categoryList.add(entity);

        entity.setId(id);

        writeCollection(categoryList);
        return id;
    }

    @Override
    public Category getByType(Category.CategoryEnum type) {
        return null;
    }

    @Override
    public void update(Category entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Category> getAll() {
        return null;
    }

    private List<Category> readCollection() {

        return (List<Category>) xstream.fromXML(file);
    }

    private void writeCollection(List<Category> newList) {
        try {
            xstream.toXML(newList, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);// TODO custom exception
        }
    }

    private long getNextId(List<Category> categoryList) {
        return categoryList.isEmpty() ? 1L : categoryList.get(
                categoryList.size() - 1).getId() + 1;
    }
}
