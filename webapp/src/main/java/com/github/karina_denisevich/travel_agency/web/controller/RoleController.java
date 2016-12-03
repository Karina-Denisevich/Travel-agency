package com.github.karina_denisevich.travel_agency.web.controller;

import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.services.RoleService;
import com.github.karina_denisevich.travel_agency.web.dto.RoleDto;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/roles")
@SuppressWarnings("unchecked")
public class RoleController extends AbstractController<Role, RoleDto, Long> {

    @Inject
    private RoleService roleService;

    @Inject
    private ConversionServiceFactoryBean conversionService;

    @RequestMapping(value = "/getByType/{roleType}", method = RequestMethod.GET)
    public ResponseEntity<RoleDto> getByEmail(@PathVariable String roleType) {
        Role.RoleEnum roleEnum = Role.RoleEnum.valueOf(roleType);

        return new ResponseEntity<>(conversionService.getObject()
                .convert(roleService.getByType(roleEnum), RoleDto.class), HttpStatus.OK);
    }
}
