package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.User;

import java.util.List;

public interface UserService {

    void save(User user);

    void saveAll(List<User> users);

    User get(Long id);
}
