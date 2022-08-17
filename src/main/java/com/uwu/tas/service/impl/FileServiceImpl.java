package com.uwu.tas.service.impl;

import com.uwu.tas.entity.Activity;
import com.uwu.tas.entity.LocationAttractionPicture;
import com.uwu.tas.entity.LocationPicture;
import com.uwu.tas.repository.ActivityRepository;
import com.uwu.tas.repository.LocationAttractionPictureRepository;
import com.uwu.tas.repository.LocationPictureRepository;
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
}
