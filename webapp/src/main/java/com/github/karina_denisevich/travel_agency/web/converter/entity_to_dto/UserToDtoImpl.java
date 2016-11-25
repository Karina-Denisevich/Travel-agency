package com.github.karina_denisevich.travel_agency.web.converter.entity_to_dto;

import com.github.karina_denisevich.travel_agency.datamodel.User;
import com.github.karina_denisevich.travel_agency.web.converter.EntityToEntity;
import com.github.karina_denisevich.travel_agency.web.dto.UserDto;

public class UserToDtoImpl extends EntityToEntity<User, UserDto> {

    @Override
    public UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        return userDto;
    }
}
