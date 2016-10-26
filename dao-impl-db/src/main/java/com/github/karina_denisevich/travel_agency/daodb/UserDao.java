package com.github.karina_denisevich.travel_agency.daodb;

import com.github.karina_denisevich.travel_agency.datamodel.User;

public interface UserDao extends GenericDao<User, Long> {

    User getByEmail(String email);

    User getWithRole(Long id);
}
