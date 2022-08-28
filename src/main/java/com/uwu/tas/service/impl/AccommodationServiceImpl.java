package com.uwu.tas.service.impl;

import com.uwu.tas.dto.accommodation.AccommodationDto;
import com.uwu.tas.entity.Accommodation;
import com.uwu.tas.entity.Vendor;
import com.uwu.tas.enums.VendorType;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.repository.AccommodationRepository;
import com.uwu.tas.repository.RoomPackageRepository;
import com.uwu.tas.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.uwu.tas.constant.TASConstants.Url.ACCOMMODATION_IMAGE_BASE_USER;

@Service
@RequiredArgsConstructor
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final RoomPackageRepository roomPackageRepository;


    @Override
    public List<AccommodationDto> getAccommodationsForVendor(Vendor vendor) {
        if (!vendor.getType().equals(VendorType.VEHICLE)) {
            return accommodationRepository.findAllByVendor(vendor).stream()
                    .map(accommodation -> new AccommodationDto(accommodation.getId(),
                            accommodation.getName(),
                            accommodation.getTelephone(),
                            accommodation.getEmail(),
                            accommodation.getRating(),
                            accommodation.getRatingCount(),
                            accommodation.getAccommodationType().getName(),
                            roomPackageRepository.countByRoom_Accommodation(accommodation),
                            accommodation.getStatus())).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public AccommodationDto getAccommodationById(long id) {
        Accommodation accommodation = accommodationRepository.findById(id).orElseThrow(() -> new CustomServiceException(404, "Accommodation not found"));
        return new AccommodationDto(accommodation.getId(),
                accommodation.getName(),
                accommodation.getTelephone(),
                accommodation.getEmail(),
                accommodation.getRating(),
                accommodation.getRatingCount(),
                accommodation.getAccommodationType().getName(),
                roomPackageRepository.countByRoom_Accommodation(accommodation),
                accommodation.getStatus(),
                accommodation.getDescription(),
                accommodation.getLocation().getName(),
                accommodation.getAddressLine1(),
                accommodation.getAddressLine2(),
                accommodation.getCity(),
                accommodation.getProvince(),
                accommodation.getPostalCode(),
                accommodation.getLatitude(),
                accommodation.getLongitude(),
                accommodation.getCheckInTime(),
                accommodation.getCheckOutTime(),
                accommodation.isParking(),
                accommodation.isSmoking(),
                accommodation.isPets(),
                getPicturesForAccommodation(accommodation));
    }

    private List<String> getPicturesForAccommodation(Accommodation accommodation) {
        return accommodation.getAccommodationPictures().stream()
                .map(accommodationPicture -> ACCOMMODATION_IMAGE_BASE_USER + "/" + accommodationPicture.getId())
                .collect(Collectors.toList());
    }


    //getReservationsForAccommodation

    //getAccommodationReservationById

}

