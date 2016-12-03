package com.github.karina_denisevich.travel_agency.web.controller;

import com.github.karina_denisevich.travel_agency.datamodel.UserDetails;
import com.github.karina_denisevich.travel_agency.web.dto.UserDetailsDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userDetails")
@SuppressWarnings("unchecked")
public class UserDetailsController extends AbstractController<UserDetails, UserDetailsDto, Long> {
}
