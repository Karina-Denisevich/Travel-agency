package com.github.karina_denisevich.travel_agency.web.controller;

import com.github.karina_denisevich.travel_agency.datamodel.Role;
import com.github.karina_denisevich.travel_agency.services.RoleService;
import com.github.karina_denisevich.travel_agency.web.dto.RoleDto;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/roles")
@SuppressWarnings("unchecked")
public class RoleController extends AbstractController<Role, RoleDto, Long> {

    @Inject
    private RoleService roleService;

    @Inject
    private ConversionServiceFactoryBean conversionService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<List<RoleDto>> getByType(@RequestParam(value = "roleType", required = false) String roleType) {
        List<Role> roleList = new ArrayList<>();
        if (roleType != null) {
            Role.RoleEnum roleEnum = Role.RoleEnum.valueOf(roleType);
            roleList.add(roleService.getByType(roleEnum));
        } else {
            roleList = new ArrayList<>(roleService.getAll());
        }

        if (CollectionUtils.isEmpty(roleList)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<RoleDto> convertedList = (List<RoleDto>) conversionService.getObject()
                .convert(roleList, TypeDescriptor.valueOf(List.class),
                        TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(RoleDto.class)));

        return new ResponseEntity<>(convertedList, HttpStatus.OK);
    }
}
