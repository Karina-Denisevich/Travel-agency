package com.github.karina_denisevich.travel_agency.web.dto;

import com.github.karina_denisevich.travel_agency.datamodel.Role;

public class RoleDto extends AbstractDto {

    private Role.RoleEnum type;

    public Role.RoleEnum getType() {
        return type;
    }

    public void setType(Role.RoleEnum type) {
        this.type = type;
    }
}
