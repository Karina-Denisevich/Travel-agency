package com.github.karina_denisevich.travel_agency.web.converter.dto_to_entity;

import com.github.karina_denisevich.travel_agency.datamodel.User;
import com.github.karina_denisevich.travel_agency.web.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class DtoToUserImpl implements DtoToEntity<UserDto, User> {

    @Override
    public User convert(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        return user;
    }
}
