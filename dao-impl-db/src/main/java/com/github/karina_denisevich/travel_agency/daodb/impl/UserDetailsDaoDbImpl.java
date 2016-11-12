package com.github.karina_denisevich.travel_agency.daodb.impl;

import com.github.karina_denisevich.travel_agency.daoapi.UserDetailsDao;
import com.github.karina_denisevich.travel_agency.daodb.unmapper.UserDetailsUnmapper;
import com.github.karina_denisevich.travel_agency.datamodel.UserDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class UserDetailsDaoDbImpl extends GenericDaoDbImpl<UserDetails, Long>
        implements UserDetailsDao {

    @Inject
    JdbcTemplate jdbcTemplate;

    public UserDetailsDaoDbImpl() {
        super(new UserDetailsUnmapper());
    }
}
