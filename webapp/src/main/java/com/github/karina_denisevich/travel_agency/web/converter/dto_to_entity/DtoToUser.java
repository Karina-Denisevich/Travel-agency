package com.github.karina_denisevich.travel_agency.web.converter.dto_to_entity;

import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import com.github.karina_denisevich.travel_agency.web.dto.UserDto;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import javax.inject.Inject;

public class DtoToUser implements Converter<UserDto, User> {

    @Inject
    private ConversionServiceFactoryBean conversionService;

    @Override
    public User convert(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(conversionService.getObject()
                .convert(userDto.getRole(), Role.class));
        return user;
    }
}
