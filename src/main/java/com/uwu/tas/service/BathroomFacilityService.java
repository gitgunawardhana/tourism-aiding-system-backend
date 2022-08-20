package com.uwu.tas.service;

import com.uwu.tas.dto.configuration.BathroomFacilityDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BathroomFacilityService {
    void createBathroomFacility(BathroomFacilityDto facilityDto);

    void changeBathroomFacilityStatus(long id);

    void updateBathroomFacility(BathroomFacilityDto facilityDto);

    void deleteBathroomFacility(long id);

    List<BathroomFacilityDto> getAllBathroomFacilities();
}
