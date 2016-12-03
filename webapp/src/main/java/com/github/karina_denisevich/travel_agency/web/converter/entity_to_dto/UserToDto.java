package com.github.karina_denisevich.travel_agency.web.converter.entity_to_dto;

import com.github.karina_denisevich.travel_agency.datamodel.User;
import com.github.karina_denisevich.travel_agency.web.dto.RoleDto;
import com.github.karina_denisevich.travel_agency.web.dto.UserDto;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import javax.inject.Inject;

public class UserToDto implements Converter<User, UserDto> {

    @Inject
    private ConversionServiceFactoryBean conversionService;

    @Override
    public UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        if (user.getRole() != null) {
            userDto.setRole(conversionService.getObject()
                    .convert(user.getRole(), RoleDto.class));
        }
        return userDto;
    }
}
