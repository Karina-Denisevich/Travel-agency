package com.github.karina_denisevich.travel_agency.web.converter.dto_to_entity;

import com.github.karina_denisevich.travel_agency.datamodel.UserDetails;
import com.github.karina_denisevich.travel_agency.web.dto.UserDetailsDto;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import javax.inject.Inject;
import java.util.Date;

public class DtoToUserDetails implements Converter<UserDetailsDto, UserDetails> {

    @Inject
    private ConversionServiceFactoryBean conversionService;

    @Override
    public UserDetails convert(UserDetailsDto userDetailsDto) {
        UserDetails userDetails = new UserDetails();
        userDetails.setId(userDetailsDto.getId());
        userDetails.setFirstName(userDetailsDto.getFirstName());
        userDetails.setLastName(userDetailsDto.getLastName());
        userDetails.setDiscount(userDetailsDto.getDiscount());
        userDetails.setbDate(conversionService.getObject()
                .convert(userDetailsDto.getbDate(), Date.class));
        userDetails.setPhone(userDetailsDto.getPhone());
        userDetails.setSkype(userDetailsDto.getSkype());
        userDetails.setUser(userDetailsDto.getUser());

        return userDetails;
    }
}
