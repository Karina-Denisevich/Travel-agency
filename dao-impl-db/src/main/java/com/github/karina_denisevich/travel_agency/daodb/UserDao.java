package com.github.karina_denisevich.travel_agency.daodb;

import com.github.karina_denisevich.travel_agency.datamodel.User;

import java.util.List;

public interface UserDao {

    User get(Long id);

    void insert(User entity);

    void update(User entity);

    void delete(Long id);

    List<User> getAll();
}
