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
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/search", method = RequestMethod.GET, params = "email")
    public ResponseEntity<UserDto> getByEmail(String email) {
        return new ResponseEntity<>(conversionService.getObject()
                .convert(userService.getByEmail(email), UserDto.class), HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, params = "role")
    public ResponseEntity<UserDto> getWithRoleById(@PathVariable Long userId,
                                                   @RequestParam(value = "role", defaultValue = "false") Boolean isWithRole) {
        if (isWithRole) {
            return new ResponseEntity<>(conversionService.getObject()
                    .convert(userService.getWithRole(userId), UserDto.class), HttpStatus.OK);
        } else {
            return super.getById(userId);
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, params = "roleType")
    public ResponseEntity<List<UserDto>> getByRole(String roleType) {
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
}
