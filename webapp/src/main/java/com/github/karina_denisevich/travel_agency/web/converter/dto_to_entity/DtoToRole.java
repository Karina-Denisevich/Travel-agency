package com.github.karina_denisevich.travel_agency.web.converter.dto_to_entity;

import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.web.dto.RoleDto;
import org.springframework.core.convert.converter.Converter;

public class DtoToRole implements Converter<RoleDto, Role> {

    @Override
    public Role convert(RoleDto roleDto) {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setType(roleDto.getType());

        return role;
    }
}
