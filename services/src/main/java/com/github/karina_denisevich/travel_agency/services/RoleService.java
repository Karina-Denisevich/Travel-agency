package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.Role;

import java.util.List;

public interface RoleService {

    void save(Role user);

    void saveAll(List<Role> roles);

    Role get(Long id);

    List<Role> getAll();

    void delete(Long id);
}