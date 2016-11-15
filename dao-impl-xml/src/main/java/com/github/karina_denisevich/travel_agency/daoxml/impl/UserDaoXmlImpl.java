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
}
