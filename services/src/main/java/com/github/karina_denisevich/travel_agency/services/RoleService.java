package com.github.karina_denisevich.travel_agency.services;

import com.github.karina_denisevich.travel_agency.datamodel.Role;

import java.util.List;

public interface RoleService extends AbstractService<Role, Long> {

    Role getByType(Role.RoleEnum type);
}
