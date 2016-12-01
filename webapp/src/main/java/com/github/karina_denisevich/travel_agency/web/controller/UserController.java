package com.github.karina_denisevich.travel_agency.web.controller;

import com.github.karina_denisevich.travel_agency.daoapi.exception.DuplicateEntityException;
import com.github.karina_denisevich.travel_agency.daoapi.exception.EmptyResultException;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import com.github.karina_denisevich.travel_agency.services.UserService;
import com.github.karina_denisevich.travel_agency.web.dto.UserDto;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/users")
@SuppressWarnings("unchecked")
public class UserController extends AbstractController<User, UserDto>{

    @Inject
    private UserService userService;

    @Inject
    private ConversionServiceFactoryBean conversionService;


}
