package com.github.karina_denisevich.travel_agency.web.controller;

import com.github.karina_denisevich.travel_agency.daoapi.exception.DuplicateEntityException;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import com.github.karina_denisevich.travel_agency.services.UserService;
import com.github.karina_denisevich.travel_agency.web.converter.dto_to_entity.DtoToEntity;
import com.github.karina_denisevich.travel_agency.web.converter.entity_to_dto.EntityToDto;
import com.github.karina_denisevich.travel_agency.web.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Inject
    private UserService userService;

    @Inject
    private EntityToDto<User, UserDto> converterToDto;

    @Inject
    private DtoToEntity<UserDto, User> converterToEntity;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getById(@PathVariable Long userId) {
        return new ResponseEntity<>(converterToDto.convert(userService.get(userId)),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getAll() {
        List<User> users = userService.getAll();
        if (CollectionUtils.isEmpty(users)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(converterToDto.convert(users), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody UserDto userDto) {
        User user = converterToEntity.convert(userDto);
        try {
            userService.save(user);
        } catch (DuplicateEntityException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    public ResponseEntity<Void> updateUser(@RequestBody UserDto userDto, @PathVariable Long userId) {
        User user = converterToEntity.convert(userDto);
        user.setId(userId);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long userId) {
        userService.get(userId);  //To handle emptyResultException
        userService.delete(userId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
