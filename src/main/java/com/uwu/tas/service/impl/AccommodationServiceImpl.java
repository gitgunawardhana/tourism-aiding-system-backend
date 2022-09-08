package com.uwu.tas.service.impl;

import com.uwu.tas.dto.accommodation.*;
import com.uwu.tas.dto.vendor.*;
import com.uwu.tas.entity.*;
import com.uwu.tas.enums.HousingFacilityType;
import com.uwu.tas.enums.VendorType;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.repository.*;
import com.uwu.tas.service.AccommodationService;
import com.uwu.tas.util.Base64Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Period;
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
    private final LocationRepository locationRepository;
    private final RoomReservationRepository roomReservationRepository;
    private final AccommodationTypeRepository accommodationTypeRepository;
    private final PublicUserRepository publicUserRepository;
    private final ReservationPackageDetailRepository reservationPackageDetailRepository;

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
                getPicturesForAccommodation(accommodation, 0));
    }

    private List<String> getPicturesForAccommodation(Accommodation accommodation, int limit) {
        if (limit == 0) {
            return accommodation.getAccommodationPictures().stream()
                    .map(accommodationPicture -> ACCOMMODATION_IMAGE_BASE_USER + "/" + accommodationPicture.getId())
                    .collect(Collectors.toList());
        } else {
            List<String> images = new ArrayList<>();
            List<AccommodationPicture> accommodationPictures = accommodation.getAccommodationPictures();
            for (int i = 0; i < limit; i++) {
                images.add(ACCOMMODATION_IMAGE_BASE_USER + "/" + accommodationPictures.get(i).getId());
            }
            return images;
        }
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
        AccommodationType accommodationType = accommodationTypeRepository.findById(vendorAccommodationBasicDetailsDto.getAccommodationTypeId()).orElseThrow(() -> new CustomServiceException("Accommodation Type Not Found"));
        Vendor vendor = optionalVendor.get();
        Accommodation accommodation = new Accommodation();
        accommodation.setName(vendorAccommodationBasicDetailsDto.getName());
        accommodation.setDescription(vendorAccommodationBasicDetailsDto.getDescription());
        accommodation.setEmail(vendorAccommodationBasicDetailsDto.getEmail());
        accommodation.setTelephone(vendorAccommodationBasicDetailsDto.getTelephone());
        accommodation.setAccommodationType(accommodationType);
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

        Location location = locationRepository.findById(vendorAccommodationLocationDetailsDto.getLocationId()).orElseThrow(() -> new CustomServiceException("Location Not FOund"));
        accommodation.setLocation(location);
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

    @Override
    public List<AccommodationSearchResultDto> searchAccommodations(AccommodationSearchDto accommodationSearchDto) {

        Period period = Period.between(accommodationSearchDto.getCheckInDate(), accommodationSearchDto.getCheckOutDate());

        Location location = locationRepository.findById(accommodationSearchDto.getLocationId()).orElseThrow(() -> new CustomServiceException("Location Not Found"));
        List<Accommodation> allAccommodations = accommodationRepository.findAllByLocation(location);

        List<AccommodationSearchResultDto> result = new ArrayList<>();
        for (Accommodation accommodation : allAccommodations) {
            if (accommodationSearchDto.getAccommodationTypes().size() == 0 || accommodationSearchDto.getAccommodationTypes().contains(accommodation.getAccommodationType().getId())) {
                RoomPackage roomPackage = null;
                boolean gotResult = false;
                while (!gotResult) {
                    List<RoomPackage> roomPackages = roomPackageRepository.searchAvailablePackageByAccommodation(
                            accommodation);
                    for (RoomPackage rp : roomPackages) {
                        long reservedCount = roomReservationRepository.checkPackageAvailability(rp,
                                accommodationSearchDto.getCheckInDate(),
                                accommodationSearchDto.getCheckOutDate());
                        if (reservedCount == 0) {
                            roomPackage = rp;
                            gotResult = true;
                            break;
                        }
                    }
                    if (!gotResult) gotResult = true;

                }
                if (roomPackage != null) {
                    Room room = roomPackage.getRoom();
                    AccommodationSearchResultDto accommodationSearchResultDto = new AccommodationSearchResultDto(
                            roomPackage.getId(),
                            accommodation.getId(),
                            accommodation.getName(),
                            room.getName(),
                            accommodation.getAddressLine1() + " " + accommodation.getBuildingNo() + " " + accommodation.getAddressLine1(),
                            getPicturesForAccommodation(accommodation, 5),
                            getAccommodationFacilities(accommodation, HousingFacilityType.OTHER),
                            room.getSize(),
                            roomPackage.getBedDetails(),
                            room.getDescription(),
                            roomPackage.isFreeCancellation(),
                            roomPackage.isPayAtProperty(),
                            roomPackage.isBreakfastIncluded(),
                            room.getView().getName(),
                            accommodation.getRating(),
                            accommodation.getRatingCount(),
                            getRatingMessageForRating(accommodation.getRating()),
                            roomPackage.getDiscount(),
                            roomPackage.getPrice().doubleValue(),
                            (roomPackage.getPrice().doubleValue() * (1 - roomPackage.getDiscount())),
                            accommodationSearchDto.getNoOfPeople(),
                            period.getDays(),
                            room.getAvailableCount(),
                            null
                    );
                    accommodationSearchResultDto.setAvailableCount(room.getAvailableCount());
                    result.add(accommodationSearchResultDto);
                }
            }
        }
        return result;
    }

    private String getRatingMessageForRating(double rating) {
        if (rating >= 9) {
            return "EXCEPTIONAL";
        } else if (rating >= 8) {
            return "EXCELLENT";
        } else if (rating >= 7) {
            return "VERY GOOD";
        } else if (rating >= 6) {
            return "GOOD";
        } else {
            return "PASSABLE";
        }

    }

    private List<String> getAccommodationFacilities(Accommodation accommodation, HousingFacilityType type) {
        if (type.equals(HousingFacilityType.ALL)) {
            return accommodationFacilityRepository.findAllByAccommodation(accommodation).stream()
                    .map(accommodationFacility -> accommodationFacility.getHousingFacility().getName())
                    .collect(Collectors.toList());
        }
        return accommodationFacilityRepository.findAllByAccommodationAndHousingFacility_Type(accommodation, type).stream()
                .map(accommodationFacility -> accommodationFacility.getHousingFacility().getName())
                .collect(Collectors.toList());
    }

    @Override
    public List<AccommodationSearchResultDto> searchPackagesForAccommodation(AccommodationSearchDto accommodationSearchDto) {

        Period period = Period.between(accommodationSearchDto.getCheckInDate(), accommodationSearchDto.getCheckOutDate());

        Accommodation accommodation = accommodationRepository.findById(accommodationSearchDto.getAccommodationId()).orElseThrow(() -> new CustomServiceException("Accommodation Not Found!"));
        List<AccommodationSearchResultDto> result = new ArrayList<>();


        List<RoomPackage> roomPackages = roomPackageRepository.searchAvailablePackageByAccommodation(
                accommodation);
        for (RoomPackage rp : roomPackages) {
            long reservedCount = roomReservationRepository.checkPackageAvailability(rp,
                    accommodationSearchDto.getCheckInDate(),
                    accommodationSearchDto.getCheckOutDate());
            if (reservedCount == 0) {
                Room room = rp.getRoom();
                AccommodationSearchResultDto accommodationSearchResultDto = new AccommodationSearchResultDto(
                        rp.getId(),
                        accommodation.getId(),
                        accommodation.getName(),
                        room.getName(),
                        accommodation.getAddressLine1() + " " + accommodation.getBuildingNo() + " " + accommodation.getAddressLine1(),
                        getPicturesForAccommodation(accommodation, 0),
                        getAccommodationFacilities(accommodation, HousingFacilityType.ALL),
                        room.getSize(),
                        rp.getBedDetails(),
                        room.getDescription(),
                        rp.isFreeCancellation(),
                        rp.isPayAtProperty(),
                        rp.isBreakfastIncluded(),
                        room.getView().getName(),
                        accommodation.getRating(),
                        accommodation.getRatingCount(),
                        getRatingMessageForRating(accommodation.getRating()),
                        rp.getDiscount(),
                        rp.getPrice().doubleValue(),
                        (rp.getPrice().doubleValue() * (1 - rp.getDiscount())),
                        rp.getNoOfPeople(),
                        period.getDays(),
                        room.getAvailableCount(),
                        null
                );
                accommodationSearchResultDto.setAvailableCount(room.getAvailableCount());
                result.add(accommodationSearchResultDto);
            }
        }
        return result;
    }

    @Override
    public AccommodationReservationViewDto getReservationViewDetails(ReservationViewRequestDto dto) {

        AccommodationReservationViewDto result = new AccommodationReservationViewDto();

        Accommodation accommodation = accommodationRepository.findById(dto.getAccommodationId()).orElseThrow(() -> new CustomServiceException("Accommodation Not Found"));
        result.setAccommodationName(accommodation.getName());
        result.setAccommodationImage(ACCOMMODATION_IMAGE_BASE_USER + "/" + accommodation.getAccommodationPictures().get(0).getId());
        result.setAccommodationAddress(accommodation.getAddressLine1() + " " + accommodation.getBuildingNo() + " " + accommodation.getCity());
        result.setDescription(accommodation.getDescription());
        result.setSelectedRooms(dto.getSelectedRoomPackages().stream().map(sr -> {
            RoomPackage rp = roomPackageRepository.findById(sr.getId()).orElseThrow(() -> new CustomServiceException("Room Package Not Found"));
            return new RoomReservationDto(
                    rp.getRoom().getName(),
                    sr.getCount(),
                    rp.getPrice().doubleValue(),
                    rp.getDiscount()
            );
        }).collect(Collectors.toList()));

        return result;
    }

    @Override
    @Transactional
    public void reserveAccommodationPackage(ReservationRequestDto dto) {

        PublicUser publicUser = publicUserRepository.findById(dto.getUserId()).orElseThrow(() -> new CustomServiceException("Public User Not Found"));

        RoomReservation roomReservation = new RoomReservation();
        roomReservation.setReservationStartDate(dto.getStartDate());
        roomReservation.setReservationEndDate(dto.getEndDate());
        roomReservation.setPublicUser(publicUser);

        double totalPrice = 0;

        List<ReservationPackageDetail> reservationPackageDetails = new ArrayList<>();

        for (SelectedRoomPackageDto sr : dto.getSelectedRoomPackages()) {
            RoomPackage rp = roomPackageRepository.findById(sr.getId()).orElseThrow(() -> new CustomServiceException("Room Package Not Found"));
            boolean exists = reservationPackageDetailRepository.existsByRoomPackageAndRoomReservation_PublicUserAndRoomReservation_ReservationStartDateAndRoomReservation_ReservationEndDate(
                    rp,
                    publicUser,
                    dto.getStartDate(),
                    dto.getEndDate()
            );
            if (exists)
                throw new CustomServiceException("You have already reserved " + rp.getRoom().getName() + " for these days");
            if (sr.getCount() != 0) {
                totalPrice += (rp.getPrice().doubleValue() * (1 - rp.getDiscount())) * sr.getCount();
                reservationPackageDetails.add(new ReservationPackageDetail(
                        rp.getPrice(),
                        rp.getDiscount(),
                        sr.getCount(),
                        roomReservation,
                        rp
                ));
            }
        }
        roomReservation.setTotalPrice(BigDecimal.valueOf(totalPrice));

        roomReservationRepository.save(roomReservation);
        reservationPackageDetailRepository.saveAll(reservationPackageDetails);

    }

}

