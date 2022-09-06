package com.uwu.tas.service.impl;

import com.uwu.tas.dto.accommodation.HousingFacilityDto;
import com.uwu.tas.entity.HousingFacility;
import com.uwu.tas.enums.HousingFacilityType;
import com.uwu.tas.repository.HousingFacilityRepository;
import com.uwu.tas.service.HousingFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HousingFacilityServiceImpl implements HousingFacilityService {

    private final HousingFacilityRepository housingFacilityRepository;

    @Override
    public List<HousingFacilityDto> getMainHousingFacilities() {
        List<HousingFacility> allFacilities = housingFacilityRepository.findAll();
        List<HousingFacilityDto> mainFacilities = new ArrayList<>();
        for (HousingFacility hf : allFacilities) {
            if (!hf.getType().equals(HousingFacilityType.SAFETY_AND_CLEANLINESS)) {
                mainFacilities.add(new HousingFacilityDto(hf.getId(), hf.getName()));
            }
        }
        return mainFacilities;
    }
}
