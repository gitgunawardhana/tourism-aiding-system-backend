package com.uwu.tas.service;

import com.uwu.tas.dto.accommodation.HousingFacilityDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HousingFacilityService {
    List<HousingFacilityDto> getMainHousingFacilities();
}
