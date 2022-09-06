package com.uwu.tas.service.impl;

import com.uwu.tas.dto.accommodation.AccommodationDto;
import com.uwu.tas.dto.accommodation.PackageDto;
import com.uwu.tas.dto.accommodation.PackagesDto;
import com.uwu.tas.dto.accommodation.RoomDto;
import com.uwu.tas.dto.vendor.*;
import com.uwu.tas.entity.*;
import com.uwu.tas.enums.VendorType;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.repository.*;
import com.uwu.tas.service.AccommodationService;
import com.uwu.tas.util.Base64Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    private final HousingFacilityRepository housingFacilityRepository;
    private final ViewRepository viewRepository;
    private final RoomRepository roomRepository;
    private final RoomPictureRepository roomPictureRepository;
    private final RoomFacilityDetailRepository roomFacilityDetailRepository;
    private final RoomBathroomFacilityDetailRepository roomBathroomFacilityDetailRepository;
    private final FacilityRepository facilityRepository;
    private final BathroomFacilityRepository bathroomFacilityRepository;

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
        System.out.println(vendorAccommodationBasicDetailsDto.getVendorId());
        Optional<Vendor> optionalVendor = vendorRepository.findById(vendorAccommodationBasicDetailsDto.getVendorId());

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

        accommodation = accommodationRepository.save(accommodation);
        vendorAccommodationBasicDetailsDto.setAccommodationId(accommodation.getId());
        return vendorAccommodationBasicDetailsDto;

    }

    @Override
    public VendorAccommodationLocationDetailsDto registerAccommodationLocationDetails(VendorAccommodationLocationDetailsDto vendorAccommodationLocationDetailsDto) {
        Optional<Accommodation> optionalAccommodation = accommodationRepository.findById(vendorAccommodationLocationDetailsDto.getAccommodationId());
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
        Optional<Accommodation> optionalAccommodation = accommodationRepository.findById(vendorAccommodationHouseRuleDetails.getAccommodationId());
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
    public void registerVendorAccommodationFacilityDetails(VendorAccommodationFacilityDetailsDto vendorAccommodationFacilityDetailsDto) {
        Optional<Accommodation> optionalAccommodation = accommodationRepository.findById(vendorAccommodationFacilityDetailsDto.getAccommodationId());
        if (!optionalAccommodation.isPresent()) {
            throw new CustomServiceException(404, "Accommodation not found");
        }
        Accommodation accommodation = optionalAccommodation.get();

        List<AccommodationFacility> accommodationFacilities = vendorAccommodationFacilityDetailsDto.getFacilities().stream().map(f -> {
            HousingFacility housingFacility = housingFacilityRepository.findById(f).orElseThrow(() -> new CustomServiceException("Housing facility not found"));
            AccommodationFacility accommodationFacility = new AccommodationFacility();
            accommodationFacility.setHousingFacility(housingFacility);
            accommodationFacility.setAccommodation(accommodation);
            return accommodationFacility;
        }).collect(Collectors.toList());

        accommodationFacilityRepository.saveAll(accommodationFacilities);

//        AccommodationFacility accommodationFacility = new AccommodationFacility();
//        accommodationFacility.setPrivateCheckInOut(vendorAccommodationFacilityDetailsDto.isPrivateCheckInOut());
//        accommodationFacility.setFrontDesk(vendorAccommodationFacilityDetailsDto.isFrontDesk());
//        accommodationFacility.setCheckIn(vendorAccommodationFacilityDetailsDto.isCheckIn());
//        accommodationFacility.setCarPark(vendorAccommodationFacilityDetailsDto.isCarPark());
//        accommodationFacility.setLaundry(vendorAccommodationFacilityDetailsDto.isLaundry());
//        accommodationFacility.setPetsAllowed(vendorAccommodationFacilityDetailsDto.isPetsAllowed());
//        accommodationFacility.setTransportAirport(vendorAccommodationFacilityDetailsDto.isTransportAirport());
//        accommodationFacility.setSecurity(vendorAccommodationFacilityDetailsDto.isSecurity());
//        accommodationFacility.setIndividualAirConditioning(vendorAccommodationFacilityDetailsDto.isIndividualAirConditioning());
//        accommodationFacility.setCleaningSupplies(vendorAccommodationFacilityDetailsDto.isCleaningSupplies());
//        accommodationFacility.setFreeFaceMasks(vendorAccommodationFacilityDetailsDto.isFreeFaceMasks());
//        accommodationFacility.setCleaningProduct(vendorAccommodationFacilityDetailsDto.isCleaningProduct());
//        accommodationFacility.setHandSanitizer(vendorAccommodationFacilityDetailsDto.isHandSanitizer());
//        accommodationFacility.setCleanedByCompany(vendorAccommodationFacilityDetailsDto.isCleanedByCompany());
//        accommodationFacility.setContactlessCheck(vendorAccommodationFacilityDetailsDto.isContactlessCheck());
//        accommodationFacility.setParking(vendorAccommodationFacilityDetailsDto.isParking());
//        accommodationFacility.setBar(vendorAccommodationFacilityDetailsDto.isBar());
//        accommodationFacility.setSmokingRoom(vendorAccommodationFacilityDetailsDto.isSmokingRoom());
//        accommodationFacility.setRestaurant(vendorAccommodationFacilityDetailsDto.isRestaurant());
//        accommodationFacility.setGym(vendorAccommodationFacilityDetailsDto.isGym());
//        accommodationFacility.setSwimmingPool(vendorAccommodationFacilityDetailsDto.isSwimmingPool());
//        accommodationFacility.setAccommodation(accommodation);

//        accommodationFacilityRepository.save(accommodationFacility);
//        return vendorAccommodationFacilityDetailsDto;
    }

    @Override
    public void saveAccommodationPicture(VendorAccommodationPictureDto vendorAccommodationPictureDto) {
        Optional<Accommodation> optionalAccommodation = accommodationRepository.findById(vendorAccommodationPictureDto.getAccommodationId());
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

    @Override
    @Transactional
    public void saveRoom(RoomDto roomDto) {
        Accommodation accommodation = accommodationRepository.findById(roomDto.getAccommodationId()).orElseThrow(() -> new CustomServiceException("Accommodation Not Found!"));
        Room room = new Room();
        room.setAccommodation(accommodation);
        room.setName(roomDto.getRoomName());
        room.setDescription(roomDto.getDescription());
        room.setSize(roomDto.getSize());
        room.setTotalCount(roomDto.getTotalRoomCount());
        room.setAvailableCount(roomDto.getTotalRoomCount());

        View view = viewRepository.findById(roomDto.getViewId()).orElseThrow(() -> new CustomServiceException("View Not Found!"));
        room.setView(view);

        List<RoomFacilityDetail> roomFacilityDetails = new ArrayList<>();
        for (long r : roomDto.getRoomFacilityIds()) {
            Facility facility = facilityRepository.findById(r).orElseThrow(() -> new CustomServiceException("Facility Not Found!"));
            RoomFacilityDetail roomFacilityDetail = new RoomFacilityDetail();
            roomFacilityDetail.setFacility(facility);
            roomFacilityDetail.setRoom(room);
            roomFacilityDetails.add(roomFacilityDetail);
        }

        List<RoomBathroomFacilityDetail> roomBathroomFacilityDetails = new ArrayList<>();
        for (long r : roomDto.getRoomFacilityIds()) {
            BathroomFacility facility = bathroomFacilityRepository.findById(r).orElseThrow(() -> new CustomServiceException("Bathroom Facility Not Found!"));
            RoomBathroomFacilityDetail roomBathroomFacilityDetail = new RoomBathroomFacilityDetail();
            roomBathroomFacilityDetail.setBathroomFacility(facility);
            roomBathroomFacilityDetail.setRoom(room);
            roomBathroomFacilityDetails.add(roomBathroomFacilityDetail);
        }

        List<RoomPicture> roomPictures = new ArrayList<>();
        for (String i : roomDto.getImages()) {
            if (i != null) {
                RoomPicture roomPicture = new RoomPicture();
                roomPicture.setImage(base64Handler.getByteArrayFromBase64(i));
                roomPicture.setRoom(room);
                roomPictures.add(roomPicture);
            }
        }
        roomRepository.save(room);
        roomFacilityDetailRepository.saveAll(roomFacilityDetails);
        roomBathroomFacilityDetailRepository.saveAll(roomBathroomFacilityDetails);
        roomPictureRepository.saveAll(roomPictures);
    }

    @Override
    public void saveRoomPackages(PackagesDto packagesDto) {
        System.out.println(packagesDto);
        Room room = roomRepository.findById(packagesDto.getRoomId()).orElseThrow(() -> new CustomServiceException("Room Not Found!"));
        List<RoomPackage> roomPackages = new ArrayList<>();
        for (PackageDto p : packagesDto.getPackages()) {
            RoomPackage roomPackage = new RoomPackage();
            roomPackage.setRoom(room);
            roomPackage.setNoOfPeople(p.getNumberOfPeople());
            roomPackage.setPrice(BigDecimal.valueOf(p.getPrice()));
            roomPackage.setDiscount(p.getDiscount());
            roomPackage.setBedDetails(p.getBedDetails());
            roomPackage.setPayAtProperty(p.isPayAtProperty());
            roomPackage.setFreeCancellation(p.isFreeCancellation());
            roomPackages.add(roomPackage);
        }
        roomPackageRepository.saveAll(roomPackages);
    }
}

