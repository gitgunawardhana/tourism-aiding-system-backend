package com.uwu.tas.service.impl;

import com.uwu.tas.dto.accommodation.AccommodationDto;
import com.uwu.tas.dto.vendor.*;
import com.uwu.tas.entity.Accommodation;
import com.uwu.tas.entity.AccommodationFacility;
import com.uwu.tas.entity.AccommodationPicture;
import com.uwu.tas.entity.Vendor;
import com.uwu.tas.enums.VendorType;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.repository.*;
import com.uwu.tas.service.AccommodationService;
import com.uwu.tas.util.Base64Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.uwu.tas.constant.TASConstants.Url.ACCOMMODATION_IMAGE_BASE_USER;

@Service
@RequiredArgsConstructor
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final RoomPackageRepository roomPackageRepository;
    private final VendorRepository vendorRepository;
    private final AccommodationFacilityRepository accommodationFacilityRepository;
    private final Base64Handler base64Handler;
    private final AccommodationPictureRepository accommodationPictureRepository;


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
                accommodation.getBuildingNo(),
                accommodation.getCity(),
                accommodation.getProvince(),
                accommodation.getPostalCode(),
                accommodation.getLatitude(),
                accommodation.getLongitude(),
                accommodation.getCheckInTime(),
                accommodation.getCheckOutTime(),
//                accommodation.isParking(),
//                accommodation.isSmoking(),
//                accommodation.isPets(),
                getPicturesForAccommodation(accommodation));
    }

    private List<String> getPicturesForAccommodation(Accommodation accommodation) {
        return accommodation.getAccommodationPictures().stream()
                .map(accommodationPicture -> ACCOMMODATION_IMAGE_BASE_USER + "/" + accommodationPicture.getId())
                .collect(Collectors.toList());
    }


    //getReservationsForAccommodation

    //getAccommodationReservationById

    @Override
    public VendorAccommodationBasicDetailsDto registerAccommodationBasicDetails(VendorAccommodationBasicDetailsDto vendorAccommodationBasicDetailsDto) {
        System.out.println(vendorAccommodationBasicDetailsDto.getId());
        Optional<Vendor> optionalVendor = vendorRepository.findById(vendorAccommodationBasicDetailsDto.getId());

        if (!optionalVendor.isPresent()) {
            throw new CustomServiceException(404, "Vendor not found");
        }
        Vendor vendor = optionalVendor.get();
        Accommodation accommodation = new Accommodation();
        accommodation.setName(vendorAccommodationBasicDetailsDto.getName());
        accommodation.setDescription(vendorAccommodationBasicDetailsDto.getDescription());
        accommodation.setEmail(vendorAccommodationBasicDetailsDto.getEmail());
        accommodation.setTelephone(vendorAccommodationBasicDetailsDto.getTelephone());
        accommodation.setVendor(vendor);

        accommodationRepository.save(accommodation);

        return vendorAccommodationBasicDetailsDto;

    }

    @Override
    public VendorAccommodationLocationDetailsDto registerAccommodationLocationDetails(VendorAccommodationLocationDetailsDto vendorAccommodationLocationDetailsDto) {
        Optional<Accommodation> optionalAccommodation = accommodationRepository.findById(vendorAccommodationLocationDetailsDto.getId());
        if (!optionalAccommodation.isPresent()) {
            throw new CustomServiceException(404, "Accommodation not found");
        }
        Accommodation accommodation = optionalAccommodation.get();

        accommodation.setAddressLine1(vendorAccommodationLocationDetailsDto.getAddressLine1());
        accommodation.setBuildingNo(vendorAccommodationLocationDetailsDto.getBuildingNo());
        accommodation.setCity(vendorAccommodationLocationDetailsDto.getCity());
        accommodation.setProvince(vendorAccommodationLocationDetailsDto.getProvince());
        accommodation.setPostalCode(vendorAccommodationLocationDetailsDto.getPostalCode());
        accommodation.setDistanceAirport(vendorAccommodationLocationDetailsDto.getDistanceAirport());
        accommodation.setDistanceCity(vendorAccommodationLocationDetailsDto.getDistanceCity());
        accommodation.setLatitude(vendorAccommodationLocationDetailsDto.getLatitude());
        accommodation.setLongitude(vendorAccommodationLocationDetailsDto.getLongitude());

        accommodationRepository.save(accommodation);
        return vendorAccommodationLocationDetailsDto;
    }

    @Override
    public VendorAccommodationHouseRuleDetails registerAccommodationHouseRuleDetails(VendorAccommodationHouseRuleDetails vendorAccommodationHouseRuleDetails) {
        Optional<Accommodation> optionalAccommodation = accommodationRepository.findById(vendorAccommodationHouseRuleDetails.getId());
        if (!optionalAccommodation.isPresent()) {
            throw new CustomServiceException(404, "Accommodation not found");
        }
        Accommodation accommodation = optionalAccommodation.get();

        accommodation.setCheckInTime(vendorAccommodationHouseRuleDetails.getCheckInTime());
        accommodation.setCheckOutTime(vendorAccommodationHouseRuleDetails.getCheckOutTime());

        accommodationRepository.save(accommodation);
        return vendorAccommodationHouseRuleDetails;

    }

    @Override
    public VendorAccommodationFacilityDetailsDto registerVendorAccommodationFacilityDetails(VendorAccommodationFacilityDetailsDto vendorAccommodationFacilityDetailsDto) {
        Optional<Accommodation> optionalAccommodation = accommodationRepository.findById(vendorAccommodationFacilityDetailsDto.getId());
        if (!optionalAccommodation.isPresent()) {
            throw new CustomServiceException(404, "Accommodation not found");
        }
        Accommodation accommodation = optionalAccommodation.get();
        AccommodationFacility accommodationFacility = new AccommodationFacility();

        accommodationFacility.setPrivateCheckInOut(vendorAccommodationFacilityDetailsDto.isPrivateCheckInOut());
        accommodationFacility.setFrontDesk(vendorAccommodationFacilityDetailsDto.isFrontDesk());
        accommodationFacility.setCheckIn(vendorAccommodationFacilityDetailsDto.isCheckIn());
        accommodationFacility.setCarPark(vendorAccommodationFacilityDetailsDto.isCarPark());
        accommodationFacility.setLaundry(vendorAccommodationFacilityDetailsDto.isLaundry());
        accommodationFacility.setPetsAllowed(vendorAccommodationFacilityDetailsDto.isPetsAllowed());
        accommodationFacility.setTransportAirport(vendorAccommodationFacilityDetailsDto.isTransportAirport());
        accommodationFacility.setSecurity(vendorAccommodationFacilityDetailsDto.isSecurity());
        accommodationFacility.setIndividualAirConditioning(vendorAccommodationFacilityDetailsDto.isIndividualAirConditioning());
        accommodationFacility.setCleaningSupplies(vendorAccommodationFacilityDetailsDto.isCleaningSupplies());
        accommodationFacility.setFreeFaceMasks(vendorAccommodationFacilityDetailsDto.isFreeFaceMasks());
        accommodationFacility.setCleaningProduct(vendorAccommodationFacilityDetailsDto.isCleaningProduct());
        accommodationFacility.setHandSanitizer(vendorAccommodationFacilityDetailsDto.isHandSanitizer());
        accommodationFacility.setCleanedByCompany(vendorAccommodationFacilityDetailsDto.isCleanedByCompany());
        accommodationFacility.setContactlessCheck(vendorAccommodationFacilityDetailsDto.isContactlessCheck());
        accommodationFacility.setParking(vendorAccommodationFacilityDetailsDto.isParking());
        accommodationFacility.setBar(vendorAccommodationFacilityDetailsDto.isBar());
        accommodationFacility.setSmokingRoom(vendorAccommodationFacilityDetailsDto.isSmokingRoom());
        accommodationFacility.setRestaurant(vendorAccommodationFacilityDetailsDto.isRestaurant());
        accommodationFacility.setGym(vendorAccommodationFacilityDetailsDto.isGym());
        accommodationFacility.setSwimmingPool(vendorAccommodationFacilityDetailsDto.isSwimmingPool());
        accommodationFacility.setAccommodation(accommodation);

        accommodationFacilityRepository.save(accommodationFacility);
        return vendorAccommodationFacilityDetailsDto;

    }

    @Override
    public void saveAccommodationPicture(VendorAccommodationPictureDto vendorAccommodationPictureDto) {
        Optional<Accommodation> optionalAccommodation = accommodationRepository.findById(vendorAccommodationPictureDto.getId());
        if (!optionalAccommodation.isPresent()) {
            throw new CustomServiceException(404, "Accommodation not found");
        }
        Accommodation accommodation = optionalAccommodation.get();

        List<AccommodationPicture> accommodationPictures = new ArrayList<>();

        for (String image : vendorAccommodationPictureDto.getImage()) {
            if (image != null) {
                AccommodationPicture accommodationPicture = new AccommodationPicture();
                accommodationPicture.setImage(base64Handler.getByteArrayFromBase64(image));
                accommodationPicture.setAccommodation(accommodation);
                accommodationPictures.add(accommodationPicture);
            }
        }
        accommodationPictureRepository.saveAll(accommodationPictures);

    }
}

