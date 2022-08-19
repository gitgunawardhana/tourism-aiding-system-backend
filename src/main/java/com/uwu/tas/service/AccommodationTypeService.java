package com.uwu.tas.service;

import com.uwu.tas.dto.configuration.AccommodationTypeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccommodationTypeService {
    void createAccommodationType(AccommodationTypeDto accommodationTypeDto);

    void changeAccommodationTypeStatus(long id);

    void updateAccommodationType(AccommodationTypeDto accommodationTypeDto);

    void deleteAccommodationType(long id);

    List<AccommodationTypeDto> getAllAccommodationTypes();
}
