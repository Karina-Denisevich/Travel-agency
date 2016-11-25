package com.github.karina_denisevich.travel_agency.web.controller;

import com.github.karina_denisevich.travel_agency.daoapi.exception.DuplicateEntityException;
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
public class UserController {

    @Inject
    private UserService userService;

    @Inject
    private ConversionServiceFactoryBean conversionService;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getById(@PathVariable Long userId) {

        return new ResponseEntity<>(conversionService.getObject().convert(userService.get(userId),
                UserDto.class), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getAll() {
        List<User> users = userService.getAll();

        if (CollectionUtils.isEmpty(users)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<UserDto> convertedList = (List<UserDto>) conversionService.getObject().convert(users,
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(User.class)),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(UserDto.class)));

        return new ResponseEntity<>(convertedList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody UserDto userDto) {

        User user = (conversionService.getObject().convert(userDto, User.class));
        try {
            userService.save(user);
        } catch (DuplicateEntityException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    public ResponseEntity<Void> updateUser(@RequestBody UserDto userDto,
                                           @PathVariable Long userId) {
        User user = (conversionService.getObject().convert(userDto, User.class));
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
