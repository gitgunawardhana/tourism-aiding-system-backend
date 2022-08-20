package com.uwu.tas.service.impl;

import com.uwu.tas.entity.*;
import com.uwu.tas.repository.*;
import com.uwu.tas.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final ActivityRepository activityRepository;
    private final LocationPictureRepository locationPictureRepository;
    private final LocationAttractionPictureRepository locationAttractionPictureRepository;
    private final AccommodationTypeRepository accommodationTypeRepository;
    private final FacilityRepository facilityRepository;
    private final BathroomFacilityRepository bathroomFacilityRepository;

    @Override
    public Resource getActivityImage(long id) {
        Optional<Activity> optional = activityRepository.findById(id);
        if (optional.isPresent()) {
            byte[] image = optional.get().getImage();
            return new ByteArrayResource(image);
        }
        return null;
    }

    @Override
    public Resource getLocationImage(long id) {
        Optional<LocationPicture> optional = locationPictureRepository.findById(id);
        if (optional.isPresent()) {
            byte[] image = optional.get().getImage();
            return new ByteArrayResource(image);
        }
        return null;
    }

    @Override
    public Resource getFirstLocationImageByLocationId(long locationId) {
        Optional<LocationPicture> optional = locationPictureRepository.getFirstLocationImageByLocationId(locationId);
        if(optional.isPresent()){
            byte[] image = optional.get().getImage();
            return new ByteArrayResource(image);
        }
        return null;
    }

    @Override
    public Resource getLocationAttractionImage(long id) {
        Optional<LocationAttractionPicture> optional = locationAttractionPictureRepository.findById(id);
        if (optional.isPresent()) {
            byte[] image = optional.get().getImage();
            return new ByteArrayResource(image);
        }
        return null;
    }

    @Override
    public Resource getAccommodationTypeImage(long id) {
        Optional<AccommodationType> optional = accommodationTypeRepository.findById(id);
        if (optional.isPresent()) {
            byte[] image = optional.get().getImage();
            return new ByteArrayResource(image);
        }
        return null;
    }

    @Override
    public Resource getFacilityImage(long id) {
        Optional<Facility> optional = facilityRepository.findById(id);
        if (optional.isPresent()) {
            byte[] image = optional.get().getImage();
            return new ByteArrayResource(image);
        }
        return null;
    }

    @Override
    public Resource getBathroomFacilityImage(long id) {
        Optional<BathroomFacility> optional = bathroomFacilityRepository.findById(id);
        if (optional.isPresent()) {
            byte[] image = optional.get().getImage();
            return new ByteArrayResource(image);
        }
        return null;
    }
}
