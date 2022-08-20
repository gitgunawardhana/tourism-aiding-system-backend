package com.uwu.tas.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public interface FileService {
    Resource getActivityImage(long id);

    Resource getLocationImage(long id);

    Resource getFirstLocationImageByLocationId(long locationId);

    Resource getLocationAttractionImage(long id);

    Resource getAccommodationTypeImage(long id);

    Resource getFacilityImage(long id);

    Resource getBathroomFacilityImage(long id);
}
