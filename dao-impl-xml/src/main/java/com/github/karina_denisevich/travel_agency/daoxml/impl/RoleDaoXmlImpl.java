package com.github.karina_denisevich.travel_agency.daoxml.impl;

import com.github.karina_denisevich.travel_agency.annotation.DbTableAnalyzer;
import com.github.karina_denisevich.travel_agency.daoapi.RoleDao;
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

@Repository
public class RoleDaoXmlImpl implements RoleDao {

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
        xstream.alias(rootName, Role.class);

        file = new File(basePath + "\\" + rootName + ".xml");
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            xstream.toXML(new ArrayList<>(), new FileOutputStream(
                    file));
        }
    }

    public RoleDaoXmlImpl() {
        this.rootName = new DbTableAnalyzer().getDbTableName(Role.class);
    }

    @Override
    public Role get(Long id) {
        List<Role> roleList = readCollection();

        for (Role role : roleList) {
            if (role.getId().equals(id)) {
                return role;
            }
        }
        //TODO: Add exception
        return null;
    }

    @Override
    public Long insert(Role entity) {
        List<Role> roleList = readCollection();
        Long id = getNextId(roleList);

        roleList.add(entity);

        entity.setId(id);

        writeCollection(roleList);
        return id;
    }


    @Override
    public Role getByType(Role.RoleEnum roleEnum) {
        List<Role> roleList = readCollection();

        for (Role role : roleList) {
            if (role.getType().equals(roleEnum)) {
                return role;
            }
        }
        return null;
    }

    @Override
    public void update(Role entity) {
//        List<Role> roleList = readCollection();
//
//        for (Role role : roleList) {
//            if (role.getId().equals(entity.getId())) {
//
//            }
//        }
    }

    @Override
    public void delete(Long id) {
        List<Role> roleList = readCollection();

        // TODO: don't iterate whole collection
        for (Role role : roleList) {
            if (role.getId().equals(id)) {
                roleList.remove(role);
                break;
            }
        }
        writeCollection(roleList);
    }

    @Override
    public List<Role> getAll() {
        return null;
    }

    private List<Role> readCollection() {

        return (List<Role>) xstream.fromXML(file);
    }

    private void writeCollection(List<Role> newList) {
        try {
            xstream.toXML(newList, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);// TODO custom exception
        }
    }

    private long getNextId(List<Role> roleList) {
        return roleList.isEmpty() ? 1L : roleList.get(
                roleList.size() - 1).getId() + 1;
    }
}
