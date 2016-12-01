package com.github.karina_denisevich.travel_agency.web.converter.entity_to_dto;

import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.web.dto.RoleDto;
import org.springframework.core.convert.converter.Converter;

public class RoleToDto implements Converter<Role, RoleDto> {

    @Override
    public RoleDto convert(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setType(role.getType());

        return roleDto;
    }
}
