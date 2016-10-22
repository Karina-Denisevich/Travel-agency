package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.daodb.UserDao;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public void insert(User entity) {

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
}
