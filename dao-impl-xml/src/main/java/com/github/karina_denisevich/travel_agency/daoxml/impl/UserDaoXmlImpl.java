package com.github.karina_denisevich.travel_agency.daoxml.impl;

import com.github.karina_denisevich.travel_agency.daoapi.UserDao;
import com.github.karina_denisevich.travel_agency.daoapi.exception.DuplicateEntityException;
import com.github.karina_denisevich.travel_agency.daoapi.exception.EmptyResultException;
import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDaoXmlImpl extends GenericDaoXmlImpl<User, Long> implements UserDao {

    public Long insert(User user) {
        List<User> userList = xmlFileIOUtils.readCollection();
        checkDuplicateEmail(user, userList);
        Long id;
        if (user.getId() == null) {
            id = getNextId(userList);
            user.setId(id);
        } else {
            id = user.getId();
        }
        userList.add(user);
        xmlFileIOUtils.writeCollection(userList);

        return id;
    }

    @SuppressWarnings("unchecked")
    private void checkDuplicateEmail(User user, List<User> entitiesFromXml) {
        for (User type :  entitiesFromXml) {
            if (type.getEmail().equals(user.getEmail())) {
                throw new DuplicateEntityException("There is already user with email = " +
                        user.getEmail());
            }
        }
    }

    @Override
    public User getByEmail(String email) {
        List<User> userList = xmlFileIOUtils.readCollection();

        for (User user : userList) {
            if (user.getEmail().equals(email))
                return user;
        }
        throw new EmptyResultException("There is no entity with email = " + email);
    }

    @Override
    public User getWithRole(Long id) {
        return get(id);
    }

    @Override
    public List<User> getByRole(Role role) {
        List<User> userList = xmlFileIOUtils.readCollection();

        List<User> userWithRoleList = userList.stream().filter
                (user -> user.getRole().getId().equals(role.getId())).collect(Collectors.toList());

        if (userWithRoleList.isEmpty()) {
            throw new EmptyResultException("There is no entities with role = " + role.getType().toString());
        }
        return userWithRoleList;
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

    @Override
    public User getByEmailWithRole(String email) {
        return getByEmail(email);
    }
}
