package com.github.karina_denisevich.travel_agency.daodb.util;

import com.github.karina_denisevich.travel_agency.daodb.GenericDao;
import com.github.karina_denisevich.travel_agency.datamodel.Role;

public interface RoleDao extends GenericDao<Role, Long> {

    Role getByType(Role.RoleEnum roleEnum);
}
