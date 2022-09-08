package com.uwu.tas.controller.file;

import com.uwu.tas.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping(value = "/images/location/location-id/{id}/{imageIndex}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource getLocationImagesByLocationId(@PathVariable(value = "id") long id, @PathVariable(value = "imageIndex") int imageIndex) {
        return fileService.getLocationImagesByLocationId(id).get(imageIndex);
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

    @GetMapping(value = "/image/accommodation/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource getAccommodationImage(@PathVariable(value = "id") long id) {
        return fileService.getAccommodationImage(id);
    }
}
