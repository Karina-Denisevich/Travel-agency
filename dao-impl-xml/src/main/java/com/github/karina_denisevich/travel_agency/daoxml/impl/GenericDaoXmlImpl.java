package com.github.karina_denisevich.travel_agency.daoxml.impl;

import com.github.karina_denisevich.travel_agency.annotation.DbTableAnalyzer;
import com.github.karina_denisevich.travel_agency.daoapi.GenericDao;
import com.github.karina_denisevich.travel_agency.daoxml.impl.converter.GenericConverter;
import com.github.karina_denisevich.travel_agency.daoxml.impl.converter.TourConverter;
import com.github.karina_denisevich.travel_agency.datamodel.AbstractModel;
import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import com.thoughtworks.xstream.XStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

@Repository
public abstract class GenericDaoXmlImpl<T extends AbstractModel, PK extends Serializable>
        implements GenericDao<T, PK> {

    private final Class<T> genericType;
    private final String rootName;
    private final GenericConverter<T> genericConverter;

    private XStream xstream;
    private File file;

    @Value("${basePath}")
    private String basePath;

    @PostConstruct
    private void initialize() throws IOException {
        xstream = new XStream();
        xstream.alias(rootName, genericType);
        xstream.registerConverter(genericConverter);
//        xstream.alias("category", Category.class);
//        xstream.addImplicitCollection(Tour.class, "categoryList");

        file = new File(basePath + "\\" + rootName + ".xml");
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            xstream.toXML(new ArrayList<>(), new FileOutputStream(
                    file));
        }
    }

    @SuppressWarnings("unchecked")
    public GenericDaoXmlImpl(GenericConverter<T> genericConverter) {
        this.genericConverter = genericConverter;

        this.genericType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];

        this.rootName = new DbTableAnalyzer().getDbTableName(genericType);
    }

    @Override
    public T get(PK id) {

        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public PK insert(T entity) {
        List<T> entityList = readCollection();
        Long id = getNextId(entityList);

        entityList.add(entity);

        entity.setId(id);

        writeCollection(entityList);

        return (PK) id;
    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void delete(PK id) {

    }

    @Override
    public List<T> getAll() {
        return null;
    }

    @SuppressWarnings("unchecked")
    protected List<T> readCollection() {
        return (List<T>) xstream.fromXML(file);
    }

    protected void writeCollection(List<T> newList) {
        try {
            xstream.toXML(newList, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);// TODO custom exception
        }
    }

    protected Long getNextId(List<T> tourList) {
        return tourList.isEmpty() ? 1L : tourList.get(
                tourList.size() - 1).getId() + 1;
    }
}
