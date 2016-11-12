package com.github.karina_denisevich.travel_agency.daoxml.impl;

import com.github.karina_denisevich.travel_agency.annotation.DbTableAnalyzer;
import com.github.karina_denisevich.travel_agency.daoapi.UserDao;
import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.datamodel.User;
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
import java.util.stream.Collectors;

@Repository
public class UserDaoXmlImpl implements UserDao {

    private final String rootName;
    private final String roleRootName;
    private XStream xstream;
    private File file;

    @Value("${basePath}")
    private String basePath;

    @PostConstruct
    private void initialize() throws IOException {
        // TODO move to the parent class
        // TODO refactoring: use classname instead of hardcoded filename
        xstream = new XStream();
        xstream.alias(rootName, User.class);

        file = new File(basePath + "\\" + rootName + ".xml");
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            xstream.toXML(new ArrayList<>(), new FileOutputStream(
                    file));
        }
    }

    public UserDaoXmlImpl() {
        this.rootName = new DbTableAnalyzer().getDbTableName(User.class);
        this.roleRootName = new DbTableAnalyzer().getDbTableName(Role.class);
    }

    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public Long insert(User entity) {
        List<User> userList = readCollection();
        Long id = getNextId(userList);

        userList.add(entity);

        entity.setId(id);

        writeCollection(userList);
        return id;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public User getWithRole(Long id) {
        return null;
    }

    @Override
    public List<User> getByRole(Role role) {
        List<User> userList = readCollection();

        return userList.stream().filter
                (user -> user.getRole().getId().equals(role.getId())).collect(Collectors.toList());
    }

    @Override
    public void insertBatch(List<User> userList) {

    }

    private List<User> readCollection() {

        return (List<User>) xstream.fromXML(file);
    }

    private void writeCollection(List<User> newList) {
        try {
            xstream.toXML(newList, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);// TODO custom exception
        }
    }

    private long getNextId(List<User> userList) {
        return userList.isEmpty() ? 1L : userList.get(
                userList.size() - 1).getId() + 1;
    }
}
