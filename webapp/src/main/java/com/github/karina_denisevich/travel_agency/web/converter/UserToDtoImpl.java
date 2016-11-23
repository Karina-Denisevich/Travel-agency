package com.github.karina_denisevich.travel_agency.web.converter;

import com.github.karina_denisevich.travel_agency.datamodel.User;
import com.github.karina_denisevich.travel_agency.web.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserToDtoImpl implements EntityToDto<User, UserDto> {

    @Override
    public UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        return userDto;
    }
}
