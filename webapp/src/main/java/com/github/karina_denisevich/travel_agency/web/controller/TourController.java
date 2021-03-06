package com.github.karina_denisevich.travel_agency.web.controller;

import com.github.karina_denisevich.travel_agency.datamodel.Tour;
import com.github.karina_denisevich.travel_agency.services.TourService;
import com.github.karina_denisevich.travel_agency.services.locale.CustomLocale;
import com.github.karina_denisevich.travel_agency.web.dto.TourDto;
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
@RequestMapping("/tours")
@SuppressWarnings("unchecked")
public class TourController extends AbstractController<Tour, TourDto, Long> {

    @Inject
    private TourService tourService;

    @Inject
    private CustomLocale customLocale;

    @Inject
    private ConversionServiceFactoryBean conversionService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<List<TourDto>> getByTitle(@RequestParam(value = "title", required = false) String title,
                                                    @RequestHeader(value = "Custom-Lang", required = false, defaultValue = "en") String language) {
        customLocale.setLanguage(language);
        List<Tour> tourList;
        if (title != null) {
            tourList = new ArrayList<>(tourService.getByTitle(title));
        } else {
            tourList = new ArrayList<>(tourService.getAll());
        }

        if (CollectionUtils.isEmpty(tourList)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<TourDto> convertedList = (List<TourDto>) conversionService.getObject()
                .convert(tourList, TypeDescriptor.valueOf(List.class),
                        TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(TourDto.class)));

        return new ResponseEntity<>(convertedList, HttpStatus.OK);
    }
}
