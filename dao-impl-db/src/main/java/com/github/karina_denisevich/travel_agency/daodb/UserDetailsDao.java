package com.github.karina_denisevich.travel_agency.daodb;

import com.github.karina_denisevich.travel_agency.datamodel.UserDetails;

import java.util.List;

public interface UserDetailsDao {

    UserDetails get(Long id);

    void insert(UserDetails entity);

    void update(UserDetails entity);

    void delete(Long id);

    List<UserDetails> getAll();
}
