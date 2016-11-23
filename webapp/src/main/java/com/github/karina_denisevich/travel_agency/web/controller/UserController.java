package com.github.karina_denisevich.travel_agency.web.controller;

import com.github.karina_denisevich.travel_agency.datamodel.User;
import com.github.karina_denisevich.travel_agency.services.UserService;
import com.github.karina_denisevich.travel_agency.web.converter.EntityToDto;
import com.github.karina_denisevich.travel_agency.web.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Inject
    private UserService userService;

    @Inject
    private EntityToDto<User, UserDto> converterToDto;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getAll() {
        return new ResponseEntity<>(converterToDto.convert(userService.getAll()), HttpStatus.OK);
    }
}
