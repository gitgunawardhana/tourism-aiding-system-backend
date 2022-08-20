package com.uwu.tas.controller.file;

import com.uwu.tas.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/file")
public class ImageController {

    private final FileService fileService;

    @GetMapping(value = "/image/activity/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource getActivityImage(@PathVariable(value = "id") long id) {
        return fileService.getActivityImage(id);
    }

    @GetMapping(value = "/image/location/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource getLocationImage(@PathVariable(value = "id") long id) {
        return fileService.getLocationImage(id);
    }

    @GetMapping(value = "/image1/location/location-id/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource getFirstLocationImageByLocationId(@PathVariable(value = "id") long id) {
        return fileService.getFirstLocationImageByLocationId(id);
    }

    @GetMapping(value = "/image2/location/location-id/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource getSecondLocationImageByLocationId(@PathVariable(value = "id") long id) {
        return fileService.getSecondLocationImageByLocationId(id);
    }

    @GetMapping(value = "/image/location/attraction/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource getLocationAttractionImage(@PathVariable(value = "id") long id) {
        return fileService.getLocationAttractionImage(id);
    }

    @GetMapping(value = "/image/accommodation-type/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource getAccommodationTypeImage(@PathVariable(value = "id") long id) {
        return fileService.getAccommodationTypeImage(id);
    }

    @GetMapping(value = "/image/facility/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource getFacilityImage(@PathVariable(value = "id") long id) {
        return fileService.getFacilityImage(id);
    }

    @GetMapping(value = "/image/bathroom-facility/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource getBathroomFacilityImage(@PathVariable(value = "id") long id) {
        return fileService.getBathroomFacilityImage(id);
    }
}
