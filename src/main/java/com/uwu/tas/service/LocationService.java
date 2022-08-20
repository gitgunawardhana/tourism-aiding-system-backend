package com.uwu.tas.service;

import com.uwu.tas.dto.location.LocationAttractionDto;
import com.uwu.tas.dto.location.LocationDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface LocationService {
    @Transactional
    void createLocation(LocationDto locationDto);

    void updateLocation(LocationDto locationDto);

    void changeLocationStatus(long id);

    List<LocationDto> getAllLocations(String text);
    List<LocationDto> getAllLocations();

    LocationDto getLocationById(long id);

    String getLocationNameById(long id);

    @Transactional
    void createLocationAttraction(LocationAttractionDto locationAttractionDto);

    void updateLocationAttraction(LocationAttractionDto locationAttractionDto);

    void changeLocationAttractionStatus(long id);

    List<LocationAttractionDto> getLocationAttractionsByLocation(long locationId, String text);

    LocationAttractionDto getLocationAttractionById(long id);
}
