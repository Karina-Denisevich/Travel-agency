package com.github.karina_denisevich.travel_agency.daoapi;

import com.github.karina_denisevich.travel_agency.datamodel.Role;

public interface RoleDao extends GenericDao<Role, Long> {

    Role getByType(Role.RoleEnum roleEnum);
}
