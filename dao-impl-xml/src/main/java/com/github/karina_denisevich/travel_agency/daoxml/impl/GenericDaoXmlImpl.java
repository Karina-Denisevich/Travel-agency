package com.github.karina_denisevich.travel_agency.daoxml.impl;

import com.github.karina_denisevich.travel_agency.annotation.DbTableAnalyzer;
import com.github.karina_denisevich.travel_agency.daoapi.GenericDao;
import com.github.karina_denisevich.travel_agency.daoapi.exception.DuplicateEntityException;
import com.github.karina_denisevich.travel_agency.daoapi.exception.EmptyResultException;
import com.github.karina_denisevich.travel_agency.daoxml.exception.XmlFileNotFoundException;
import com.github.karina_denisevich.travel_agency.datamodel.AbstractModel;
import com.github.karina_denisevich.travel_agency.datamodel.User;
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
    // private final GenericConverter<T> genericConverter;

    private XStream xstream;
    private File file;

    @Value("${basePath}")
    private String basePath;

    @PostConstruct
    private void initialize() throws IOException {
        xstream = new XStream();
        xstream.alias(rootName, genericType);
        //xstream.registerConverter(genericConverter);

        file = new File(basePath + "\\" + rootName + ".xml");
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            xstream.toXML(new ArrayList<>(), new FileOutputStream(
                    file));
        }
    }

    @SuppressWarnings("unchecked")
    public GenericDaoXmlImpl() {
        // this.genericConverter = genericConverter;

        this.genericType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];

        this.rootName = new DbTableAnalyzer().getDbTableName(genericType);
    }

    @Override
    public T get(PK id) {
        List<T> entityList = readCollection();

        for (T entity : entityList) {
            if (entity.getId().equals(id)) {
                return entity;
            }
        }
        throw new EmptyResultException("There is no entity with id = " + id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public PK insert(T entity) {
        List<T> entityList = readCollection();
        checkDuplicateEmail(entity, entityList);
        Long id;
        if (entity.getId() == null) {
            id = getNextId(entityList);
            entity.setId(id);
        } else {
            id = entity.getId();
        }

        entityList.add(entity);
        writeCollection(entityList);

        return (PK) id;
    }

    @Override
    public void update(T entity) {
        List<T> entityList = readCollection();

        for (T t : entityList) {
            if (t.getId().equals(entity.getId())) {
                entityList.set(entityList.indexOf(t), entity);
                break;
            }
        }
        writeCollection(entityList);
    }

    @Override
    public void delete(PK id) {
        List<T> entityList = readCollection();

        for (T entity : entityList) {
            if (entity.getId().equals(id)) {
                entityList.remove(entity);
                break;
            }
        }
        writeCollection(entityList);
    }

    @Override
    public List<T> getAll() {
        return readCollection();
    }

    @SuppressWarnings("unchecked")
    private void checkDuplicateEmail(T entity, List<T> entitiesFromXml) {
        Class<T> genericType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        User user;
        if (genericType.getSimpleName().equals("User")) {
            user = (User) entity;
        } else {
            return;
        }
        for (User type : (List<User>) entitiesFromXml) {
            if (type.getEmail().equals(user.getEmail())) {
                throw new DuplicateEntityException("There is already user with email = " +
                        user.getEmail());
            }
        }

    }

    @SuppressWarnings("unchecked")
    protected List<T> readCollection() {
        return (List<T>) xstream.fromXML(file);
    }

    protected void writeCollection(List<T> newList) {
        try {
            xstream.toXML(newList, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new XmlFileNotFoundException("Some problems with creating xml file.");
        }
    }

    protected Long getNextId(List<T> tourList) {
        return tourList.isEmpty() ? 1L : tourList.get(
                tourList.size() - 1).getId() + 1;
    }
}
