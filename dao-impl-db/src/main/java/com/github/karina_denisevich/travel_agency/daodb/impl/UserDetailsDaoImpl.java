package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.daodb.UserDetailsDao;
import com.github.karina_denisevich.travel_agency.datamodel.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDetailsDaoImpl implements UserDetailsDao {

    @Override
    public UserDetails get(Long id) {
        return null;
    }

    @Override
    public Long insert(UserDetails entity) {
        return null;
    }

    @Override
    public void update(UserDetails entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<UserDetails> getAll() {
        return null;
    }

    @Override
    public void insertBatch(List<UserDetails> userDetailses) {

    }
}
