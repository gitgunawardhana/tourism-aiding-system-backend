package com.uwu.tas.service;

import com.uwu.tas.dto.configuration.FacilityDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FacilityService {
    void createFacility(FacilityDto facilityDto);

    void changeFacilityStatus(long id);

    void updateFacility(FacilityDto facilityDto);

    void deleteFacility(long id);

    List<FacilityDto> getAllFacilities();
}
