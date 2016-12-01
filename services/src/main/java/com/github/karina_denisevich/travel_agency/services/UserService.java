package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.datamodel.User;

import java.util.List;

public interface UserService extends AbstractService<User>{

    User getByEmail(String email);

    User getByEmailWithRole(String email);

    List<User> getByRole(Role role);

    User getWithRole(Long id);
}
