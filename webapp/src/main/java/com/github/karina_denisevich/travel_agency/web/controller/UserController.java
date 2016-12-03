package com.github.karina_denisevich.travel_agency.web.controller;

import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.datamodel.User;
import com.github.karina_denisevich.travel_agency.services.UserService;
import com.github.karina_denisevich.travel_agency.web.dto.UserDto;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/users")
@SuppressWarnings("unchecked")
public class UserController extends AbstractController<User, UserDto, Long> {

    @Inject
    private UserService userService;

    @Inject
    private ConversionServiceFactoryBean conversionService;

    @RequestMapping(value = "/getByEmail/{email}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getByEmail(@PathVariable String email) {
        return new ResponseEntity<>(conversionService.getObject()
                .convert(userService.getByEmail(email), UserDto.class), HttpStatus.OK);
    }

    @RequestMapping(value = "/getWithRole/{userId}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getWithRoleById(@PathVariable Long userId) {
        return new ResponseEntity<>(conversionService.getObject()
                .convert(userService.getWithRole(userId), UserDto.class), HttpStatus.OK);
    }

    @RequestMapping(value = "/getByRole/{roleType}", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getByRole(@PathVariable String roleType) {
        Role role = new Role();
        role.setType(Role.RoleEnum.valueOf(roleType));

        List<User> userList = userService.getByRole(role);
        if (CollectionUtils.isEmpty(userList)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<UserDto> convertedList = (List<UserDto>) conversionService.getObject()
                .convert(userList, TypeDescriptor.valueOf(List.class),
                        TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(UserDto.class)));

        return new ResponseEntity<>(convertedList, HttpStatus.OK);
    }

    @RequestMapping(value = "/getWithRoleByEmail/{email}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getWithRoleByEmail(@PathVariable String email) {
        return new ResponseEntity<>(conversionService.getObject()
                .convert(userService.getByEmailWithRole(email), UserDto.class), HttpStatus.OK);
    }
}
