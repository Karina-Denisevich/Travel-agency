package com.github.karina_denisevich.travel_agency.web.controller;

import com.github.karina_denisevich.travel_agency.daoapi.exception.EmptyResultException;
import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import com.github.karina_denisevich.travel_agency.services.UserService;
import com.github.karina_denisevich.travel_agency.web.dto.RoleDto;
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
public class UserController extends AbstractController<User, UserDto> {

    @Inject
    private UserService userService;

    @Inject
    private ConversionServiceFactoryBean conversionService;

    @RequestMapping(value = "/getByEmail", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getByEmail(@RequestParam("email") String email) {
        return new ResponseEntity<>(conversionService.getObject()
                .convert(userService.getByEmail(email), UserDto.class), HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getWithRoleById(@PathVariable Long userId) {
        return new ResponseEntity<>(conversionService.getObject()
                .convert(userService.getWithRole(userId), UserDto.class), HttpStatus.OK);
    }

    @RequestMapping(value = "/getByRole", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getByRole(@RequestParam("role") String roleParam) {
        Role role = new Role();
        role.setType(Role.RoleEnum.valueOf(roleParam));

        List<User> userList;
        try {
            userList = userService.getByRole(role);
        } catch (EmptyResultException ex) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<UserDto> convertedList = (List<UserDto>) conversionService.getObject()
                .convert(userList, TypeDescriptor.valueOf(List.class),
                        TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(UserDto.class)));

        return new ResponseEntity<>(convertedList, HttpStatus.OK);
    }
//
//    @Override
//    public User getByEmailWithRole(String email) {
//        return userDao.getByEmailWithRole(email);
//    }
}
