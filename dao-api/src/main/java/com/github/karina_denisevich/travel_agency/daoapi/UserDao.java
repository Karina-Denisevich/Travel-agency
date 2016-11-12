package com.github.karina_denisevich.travel_agency.daoapi;

import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.datamodel.User;

import java.util.List;

public interface UserDao extends GenericDao<User, Long> {

    User getByEmail(String email);

    User getWithRole(Long id);

    List<User> getByRole(Role role);

    void insertBatch(List<User> userList);
}
