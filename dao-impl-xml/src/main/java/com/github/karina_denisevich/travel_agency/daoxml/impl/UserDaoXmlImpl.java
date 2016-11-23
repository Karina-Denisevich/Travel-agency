package com.github.karina_denisevich.travel_agency.daoxml.impl;

import com.github.karina_denisevich.travel_agency.daoapi.UserDao;
import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDaoXmlImpl extends GenericDaoXmlImpl<User, Long> implements UserDao {

    @Override
    public User getByEmail(String email) {
        List<User> userList = xmlFileIOUtils.readCollection();

        for (User user : userList) {
            if (user.getEmail().equals(email))
                return user;
        }
        return null;
    }

    @Override
    public User getWithRole(Long id) {

        return get(id);
    }

    @Override
    public List<User> getByRole(Role role) {
        List<User> userList = xmlFileIOUtils.readCollection();

        return userList.stream().filter
                (user -> user.getRole().getId().equals(role.getId())).collect(Collectors.toList());
    }

    @Override
    public void insertBatch(List<User> userList) {
        List<User> entityList = xmlFileIOUtils.readCollection();
        for (User user : userList) {
            checkDuplicateEmail(user, entityList);
            if (user.getId() == null) {
                user.setId(getNextId(entityList));
            }
            entityList.add(user);
        }
        xmlFileIOUtils.writeCollection(entityList);
    }
}
