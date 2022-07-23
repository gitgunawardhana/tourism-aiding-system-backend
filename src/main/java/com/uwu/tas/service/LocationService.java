package com.uwu.tas.service;

import com.uwu.tas.dto.location.LocationAttractionDto;
import com.uwu.tas.dto.location.LocationDto;
import com.uwu.tas.enums.VisibilityStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface LocationService {
    @Transactional
    void createLocation(LocationDto locationDto);

    void updateLocation(LocationDto locationDto);

    void changeLocationStatus(long id, VisibilityStatus status);

    List<LocationDto> getAllLocations(String text);

    LocationDto getLocationById(long id);

    @Transactional
    void createLocationAttraction(LocationAttractionDto locationAttractionDto);

    void updateLocationAttraction(LocationAttractionDto locationAttractionDto);

    void changeLocationAttractionStatus(long id, VisibilityStatus status);

    List<LocationAttractionDto> getAllLocationAttractions(String text);

    LocationAttractionDto getLocationAttractionById(long id);
}
