package com.github.karina_denisevich.travel_agency.web.controller;

import com.github.karina_denisevich.travel_agency.datamodel.AbstractModel;
import com.github.karina_denisevich.travel_agency.datamodel.UserDetails;
import com.github.karina_denisevich.travel_agency.services.UserDetailsService;
import com.github.karina_denisevich.travel_agency.web.dto.UserDetailsDto;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/user/details")
@SuppressWarnings("unchecked")
public class UserDetailsController extends AbstractController<UserDetails, UserDetailsDto, Long> {

    @Inject
    private UserDetailsService userDetailsService;

    @Inject
    private ConversionServiceFactoryBean conversionService;

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody UserDetailsDto userDetailsDto,
                                         @PathVariable Long id) {
        UserDetails userDetails = (conversionService.getObject().convert(userDetailsDto, UserDetails.class));
        userDetails.setId(id);
        userDetailsService.save(userDetails);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/saveAll", method = RequestMethod.POST, params = "id")
    public ResponseEntity<Object> createBatch(@RequestBody List<UserDetailsDto> entityDtoList,
                                              @RequestParam("id") Long[] idArr) {
        List<UserDetails> convertedList = (List<UserDetails>) conversionService.getObject()
                .convert(entityDtoList, TypeDescriptor.valueOf(List.class),
                        TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(UserDetails.class)));
        setIdArr(convertedList, idArr);
        userDetailsService.saveAll(convertedList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private void setIdArr(List<? extends AbstractModel> targetList, Long[] idArr) {
        IntStream.range(0, idArr.length).forEach(i -> targetList.get(i).setId(idArr[i]));
    }
}
