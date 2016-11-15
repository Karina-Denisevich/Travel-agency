package com.github.karina_denisevich.travel_agency.daoxml.impl;

import com.github.karina_denisevich.travel_agency.daoapi.UserDetailsDao;
import com.github.karina_denisevich.travel_agency.datamodel.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDetailsDaoXmlImpl extends GenericDaoXmlImpl<UserDetails, Long> implements UserDetailsDao {

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
}
