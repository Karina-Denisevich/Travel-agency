package com.github.karina_denisevich.travel_agency.web.converter.entity_to_dto;

import com.github.karina_denisevich.travel_agency.datamodel.UserDetails;
import com.github.karina_denisevich.travel_agency.web.dto.UserDetailsDto;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import javax.inject.Inject;

public class UserDetailsToDto implements Converter<UserDetails, UserDetailsDto> {

    @Inject
    private ConversionServiceFactoryBean conversionService;

    @Override
    public UserDetailsDto convert(UserDetails userDetails) {
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setId(userDetails.getId());
        userDetailsDto.setFirstName(userDetails.getFirstName());
        userDetailsDto.setLastName(userDetails.getLastName());
        userDetailsDto.setDiscount(userDetails.getDiscount());
        userDetailsDto.setbDate(conversionService.getObject()
                .convert(userDetails.getbDate(), String.class));
        userDetailsDto.setPhone(userDetails.getPhone());
        userDetailsDto.setSkype(userDetails.getSkype());

        return userDetailsDto;
    }
}

