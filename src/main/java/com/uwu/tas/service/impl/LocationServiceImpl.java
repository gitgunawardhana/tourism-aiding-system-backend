package com.uwu.tas.service.impl;

import com.uwu.tas.dto.configuration.ActivityDto;
import com.uwu.tas.dto.location.LocationAttractionDto;
import com.uwu.tas.dto.location.LocationDto;
import com.uwu.tas.dto.location.LocationNameDto;
import com.uwu.tas.entity.*;
import com.uwu.tas.enums.VisibilityStatus;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.repository.*;
import com.uwu.tas.service.LocationService;
import com.uwu.tas.util.Base64Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.uwu.tas.constant.TASConstants.Url.*;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final LocationPictureRepository locationPictureRepository;
    private final LocationAttractionRepository locationAttractionRepository;
    private final LocationAttractionPictureRepository locationAttractionPictureRepository;
    private final ProvinceRepository provinceRepository;
    private final ActivityRepository activityRepository;
    private final ActivityLocationDetailRepository activityLocationDetailRepository;

    private final Base64Handler base64Handler;

    @Override
    @Transactional
    public void createLocation(LocationDto locationDto) {

        if (locationRepository.existsByName(locationDto.getName()))
            throw new CustomServiceException(409, "There is an existing location for given name!");

        Location location = new Location();
        location.setName(locationDto.getName());
        location.setLongitude(locationDto.getLongitude());
        location.setLatitude(locationDto.getLatitude());
        location.setDescription(locationDto.getDescription());
        location.setMinimumSpendingDays(locationDto.getMinimumSpendingDays());
        location.setVisibilityStatus(VisibilityStatus.VISIBLE);

        Province province = provinceRepository.findById(locationDto.getProvinceId()).orElseThrow(() -> new CustomServiceException(404, "Province not found"));
        location.setProvince(province);

        List<LocationPicture> locationPictures = new ArrayList<>();
        for (String picture : locationDto.getLocationPictures()) {
            if (picture != null) {
                LocationPicture locationPicture = new LocationPicture();
                locationPicture.setImage(base64Handler.getByteArrayFromBase64(picture));
                locationPicture.setLocation(location);
                locationPictures.add(locationPicture);
            }
        }

        List<ActivityLocationDetail> activityLocationDetails = new ArrayList<>();
        for (ActivityDto activityDto : locationDto.getLocationActivities()) {
            ActivityLocationDetail activityLocationDetail = new ActivityLocationDetail();
            Activity activity = activityRepository.findById(activityDto.getId()).orElseThrow(() -> new CustomServiceException(404, "Activity not found"));
            activityLocationDetail.setActivity(activity);
            activityLocationDetail.setLocation(location);
            activityLocationDetails.add(activityLocationDetail);
        }

        locationRepository.save(location);
        locationPictureRepository.saveAll(locationPictures);
        activityLocationDetailRepository.saveAll(activityLocationDetails);
    }

    @Override
    public void updateLocation(LocationDto locationDto) {

        Location location = locationRepository.findById(locationDto.getId()).orElseThrow(() -> new CustomServiceException(404, "Location not found"));

        Optional<Location> optionalLocation = locationRepository.findByName(locationDto.getName());
        if (optionalLocation.isPresent()) {
            if (!optionalLocation.get().getName().equals(location.getName()))
                throw new CustomServiceException(409, "There is an existing location for given name!");
        }

        location.setName(locationDto.getName());
        location.setLongitude(locationDto.getLongitude());
        location.setLatitude(locationDto.getLatitude());
        location.setDescription(locationDto.getDescription());
        location.setMinimumSpendingDays(locationDto.getMinimumSpendingDays());

        Province province = provinceRepository.findById(locationDto.getProvinceId()).orElseThrow(() -> new CustomServiceException(404, "Province not found"));
        location.setProvince(province);

        List<LocationPicture> locationPictures = new ArrayList<>();
        for (String picture : locationDto.getLocationPictures()) {
            if (picture != null) {
                LocationPicture locationPicture = new LocationPicture();
                locationPicture.setImage(base64Handler.getByteArrayFromBase64(picture));
                locationPicture.setLocation(location);
                locationPictures.add(locationPicture);
            }
        }

        List<ActivityLocationDetail> activityLocationDetails = new ArrayList<>();
        for (ActivityDto activityDto : locationDto.getLocationActivities()) {
            ActivityLocationDetail activityLocationDetail = new ActivityLocationDetail();
            Activity activity = activityRepository.findById(activityDto.getId()).orElseThrow(() -> new CustomServiceException(404, "Activity not found"));
            activityLocationDetail.setActivity(activity);
            activityLocationDetail.setLocation(location);
            activityLocationDetails.add(activityLocationDetail);
        }

        locationRepository.save(location);
        locationPictureRepository.saveAll(locationPictures);
        activityLocationDetailRepository.saveAll(activityLocationDetails);

    }

    @Override
    public void changeLocationStatus(long id) {
        Location location = locationRepository.findById(id).orElseThrow(() -> new CustomServiceException(404, "Location not found"));
        VisibilityStatus status = VisibilityStatus.VISIBLE;
        if (location.getVisibilityStatus().equals(VisibilityStatus.VISIBLE)) status = VisibilityStatus.NOT_VISIBLE;
        location.setVisibilityStatus(status);
        locationRepository.save(location);
    }

    @Override
    public List<LocationDto> getAllLocations(String text) {
        List<Location> locations = locationRepository.findByNameLike(text);
        return locations.stream().map(location -> {
            LocationDto locationDto = new LocationDto();
            locationDto.setId(location.getId());
            locationDto.setName(location.getName());
            locationDto.setLongitude(location.getLongitude());
            locationDto.setLatitude(location.getLatitude());
            locationDto.setDescription(location.getDescription());
            locationDto.setMinimumSpendingDays(location.getMinimumSpendingDays());
            locationDto.setVisibilityStatus(location.getVisibilityStatus());
            locationDto.setProvinceId(location.getProvince().getId());
            locationDto.setProvinceName(location.getProvince().getName());
            locationDto.setModifiedDateTime(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                    .format(location.getUpdatedDateTime()));
            locationDto.setNumberOfActivities(activityLocationDetailRepository.countByLocation(location));
            locationDto.setNumberOfAttractions(locationAttractionRepository.countByLocation(location));
            return locationDto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<LocationDto> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        return locations.stream().map(location -> {
            LocationDto locationDto = new LocationDto();
            locationDto.setId(location.getId());
            locationDto.setName(location.getName());
            locationDto.setLongitude(location.getLongitude());
            locationDto.setLatitude(location.getLatitude());
            locationDto.setDescription(location.getDescription());
            locationDto.setMinimumSpendingDays(location.getMinimumSpendingDays());
            locationDto.setVisibilityStatus(location.getVisibilityStatus());
            locationDto.setProvinceId(location.getProvince().getId());
            locationDto.setProvinceName(location.getProvince().getName());
            locationDto.setModifiedDateTime(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                    .format(location.getUpdatedDateTime()));
            locationDto.setNumberOfActivities(activityLocationDetailRepository.countByLocation(location));

            List<ActivityLocationDetail> byLocationAndActivity_visibilityStatus = activityLocationDetailRepository.findByLocationAndActivity_VisibilityStatus(location, VisibilityStatus.VISIBLE);

            ArrayList<Long> activityIds = new ArrayList<>();
            ArrayList<ActivityDto> activityDtos = new ArrayList<>();

            for (ActivityLocationDetail activityLocationDetail : byLocationAndActivity_visibilityStatus) {
                ActivityDto activityDto = new ActivityDto();
                activityDto.setId(activityLocationDetail.getActivity().getId());
                activityDto.setActivityName(activityLocationDetail.getActivity().getActivityName());
                activityDto.setImage(ACTIVITY_IMAGE_BASE_URL + "/" + activityLocationDetail.getActivity().getId());
                activityDto.setStatus(activityLocationDetail.getActivity().getVisibilityStatus());
                activityDto.setChecked(false);

                activityIds.add(activityLocationDetail.getActivity().getId());
                activityDtos.add(activityDto);
            }
            locationDto.setLocationActivities(activityDtos);
            locationDto.setLocationActivitiesId(activityIds);
            locationDto.setNumberOfAttractions(locationAttractionRepository.countByLocation(location));
            return locationDto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<LocationDto> getTopLocations() {
        List<Location> locations = locationRepository.findTopLocations();
        return locations.stream().map(location1 -> {
            LocationDto locationDto = new LocationDto();
            locationDto.setId(location1.getId());
            locationDto.setName(location1.getName());
            locationDto.setLongitude(location1.getLongitude());
            locationDto.setLatitude(location1.getLatitude());
            locationDto.setDescription(location1.getDescription());
            locationDto.setMinimumSpendingDays(location1.getMinimumSpendingDays());
            locationDto.setVisibilityStatus(location1.getVisibilityStatus());
            locationDto.setProvinceId(location1.getProvince().getId());
            locationDto.setProvinceName(location1.getProvince().getName());
            locationDto.setModifiedDateTime(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                    .format(location1.getUpdatedDateTime()));
            locationDto.setNumberOfActivities(activityLocationDetailRepository.countByLocation(location1));
            locationDto.setNumberOfAttractions(locationAttractionRepository.countByLocation(location1));
            return locationDto;
        }).collect(Collectors.toList());
    }

    @Override
    public LocationDto getLocationById(long id) {
        Location location = locationRepository.findById(id).orElseThrow(() -> new CustomServiceException(404, "Location not found"));
        LocationDto locationDto = new LocationDto();
        locationDto.setId(location.getId());
        locationDto.setName(location.getName());
        locationDto.setLongitude(location.getLongitude());
        locationDto.setLatitude(location.getLatitude());
        locationDto.setDescription(location.getDescription());
        locationDto.setMinimumSpendingDays(location.getMinimumSpendingDays());
        locationDto.setVisibilityStatus(location.getVisibilityStatus());
        locationDto.setProvinceId(location.getProvince().getId());
        locationDto.setProvinceName(location.getProvince().getName());

        List<String> locationPictureUrls = new ArrayList<>();
        List<LocationPicture> locationPictures = location.getLocationPictures();
        for (LocationPicture locationPicture : locationPictures) {
            locationPictureUrls.add(LOCATION_IMAGE_BASE_URL + "/" + locationPicture.getId());
        }
        locationDto.setLocationPictures(locationPictureUrls);

        locationDto.setNumberOfActivities(activityLocationDetailRepository.countByLocation(location));
        List<Activity> activities = activityRepository.findByVisibilityStatus(VisibilityStatus.VISIBLE);
        List<ActivityDto> activitiesForLocation = activities.stream().map(activity -> {
            return new ActivityDto(
                    activity.getId(),
                    activity.getActivityName(),
                    ACTIVITY_IMAGE_BASE_URL + "/" + activity.getId(),
                    activity.getVisibilityStatus(),
                    activityLocationDetailRepository.existsByLocationAndActivity(location, activity)
            );
        }).collect(Collectors.toList());
        locationDto.setLocationActivities(activitiesForLocation);

        locationDto.setNumberOfAttractions(locationAttractionRepository.countByLocation(location));
        List<LocationAttractionDto> locationAttractionDtos = new ArrayList<>();
        List<LocationAttraction> locationAttractions = locationAttractionRepository.findByLocation(location);
        for (LocationAttraction locationAttraction : locationAttractions) {
            LocationAttractionDto locationAttractionDto = new LocationAttractionDto(
                    locationAttraction.getId(),
                    locationAttraction.getName(),
                    locationAttraction.getDescription(),
                    locationAttraction.getTelephone(),
                    locationAttraction.getEmail(),
                    locationAttraction.getWebsite(),
                    locationAttraction.getVisibilityStatus(),
                    DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                            .format(locationAttraction.getUpdatedDateTime()),
                    location.getId(),
                    locationAttractionPictureRepository.findByLocationAttraction(locationAttraction).stream()
                            .map(locationAttractionPicture -> LOCATION_ATTRACTION_IMAGE_BASE_URL + "/" + locationAttractionPicture.getId())
                            .collect(Collectors.toList())
            );
            locationAttractionDtos.add(locationAttractionDto);
        }
        locationDto.setLocationAttractions(locationAttractionDtos);

        return locationDto;
    }

    @Override
    public String getLocationNameById(long id) {
        Location location = locationRepository.findById(id).orElseThrow(() -> new CustomServiceException(404, "Location not found"));
        return location.getName();
    }

    @Override
    public List<LocationNameDto> getAllLocationNames() {
        return locationRepository.findAll().stream().map(location -> new LocationNameDto(location.getId(), location.getName())).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void createLocationAttraction(LocationAttractionDto locationAttractionDto) {
        if (locationAttractionRepository.existsByName(locationAttractionDto.getName())) {
            throw new CustomServiceException(409, "There is an existing location attraction for given name!");
        }
        Location location = locationRepository.findById(locationAttractionDto.getLocationId()).orElseThrow(() -> new CustomServiceException(404, "Location not found"));

        LocationAttraction locationAttraction = new LocationAttraction();
        locationAttraction.setName(locationAttractionDto.getName());
        locationAttraction.setDescription(locationAttractionDto.getDescription());
        locationAttraction.setTelephone(locationAttractionDto.getTelephone());
        locationAttraction.setEmail(locationAttractionDto.getEmail());
        locationAttraction.setWebsite(locationAttractionDto.getWebsite());
        locationAttraction.setVisibilityStatus(VisibilityStatus.VISIBLE);
        locationAttraction.setLocation(location);

        List<LocationAttractionPicture> locationAttractionPictures = new ArrayList<>();
        for (String picture : locationAttractionDto.getLocationAttractionPictures()) {
            LocationAttractionPicture locationAttractionPicture = new LocationAttractionPicture();
            locationAttractionPicture.setImage(base64Handler.getByteArrayFromBase64(picture));
            locationAttractionPicture.setLocationAttraction(locationAttraction);
            locationAttractionPictures.add(locationAttractionPicture);
        }

        locationAttractionRepository.save(locationAttraction);
        locationAttractionPictureRepository.saveAll(locationAttractionPictures);
    }

    @Override
    public void updateLocationAttraction(LocationAttractionDto locationAttractionDto) {

        LocationAttraction locationAttraction = locationAttractionRepository.findById(locationAttractionDto.getId()).orElseThrow(() -> new CustomServiceException(404, "Location attraction not found"));

        Optional<LocationAttraction> optionalLocationAttraction = locationAttractionRepository.findByName(locationAttractionDto.getName());
        if (optionalLocationAttraction.isPresent()) {
            if (!optionalLocationAttraction.get().getName().equals(locationAttraction.getName())) {
                throw new CustomServiceException(409, "There is another existing location attraction for given name!");
            }
        }
        locationAttraction.setName(locationAttractionDto.getName());
        locationAttraction.setDescription(locationAttractionDto.getDescription());
        locationAttraction.setTelephone(locationAttractionDto.getTelephone());
        locationAttraction.setEmail(locationAttractionDto.getEmail());
        locationAttraction.setWebsite(locationAttractionDto.getWebsite());

        List<LocationAttractionPicture> locationAttractionPictures = new ArrayList<>();
        for (String picture : locationAttractionDto.getLocationAttractionPictures()) {
            LocationAttractionPicture locationAttractionPicture = new LocationAttractionPicture();
            locationAttractionPicture.setImage(base64Handler.getByteArrayFromBase64(picture));
            locationAttractionPicture.setLocationAttraction(locationAttraction);
            locationAttractionPictures.add(locationAttractionPicture);
        }

        locationAttractionRepository.save(locationAttraction);
        locationAttractionPictureRepository.saveAll(locationAttractionPictures);
    }

    @Override
    public void changeLocationAttractionStatus(long id) {
        LocationAttraction locationAttraction = locationAttractionRepository.findById(id)
                .orElseThrow(() -> new CustomServiceException(404, "Location attraction not found"));
        VisibilityStatus status = VisibilityStatus.VISIBLE;
        if (locationAttraction.getVisibilityStatus().equals(VisibilityStatus.VISIBLE))
            status = VisibilityStatus.NOT_VISIBLE;
        locationAttraction.setVisibilityStatus(status);
        locationAttractionRepository.save(locationAttraction);
    }

    @Override
    public List<LocationAttractionDto> getLocationAttractionsByLocation(long locationId, String text) {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new CustomServiceException(404, "Location not found"));
        List<LocationAttraction> attractions = locationAttractionRepository.findByLocationAndNameLike(location, text);
        return attractions.stream().map(attraction -> new LocationAttractionDto(
                attraction.getId(),
                attraction.getName(),
                attraction.getDescription(),
                attraction.getTelephone(),
                attraction.getEmail(),
                attraction.getWebsite(),
                attraction.getVisibilityStatus(),
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                        .format(attraction.getUpdatedDateTime()),
                attraction.getLocation().getId(),
                locationAttractionPictureRepository.findByLocationAttraction(attraction).stream()
                        .map(locationAttractionPicture -> LOCATION_ATTRACTION_IMAGE_BASE_URL + "/" + locationAttractionPicture.getId())
                        .collect(Collectors.toList())
        )).collect(Collectors.toList());
    }

    @Override
    public List<LocationAttractionDto> getAllLocationAttractions() {
        List<LocationAttraction> attractions = locationAttractionRepository.findAll();
        return attractions.stream().map(attraction -> new LocationAttractionDto(
                attraction.getId(),
                attraction.getName(),
                attraction.getDescription(),
                attraction.getTelephone(),
                attraction.getEmail(),
                attraction.getWebsite(),
                attraction.getVisibilityStatus(),
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                        .format(attraction.getUpdatedDateTime()),
                attraction.getLocation().getId(),
                locationAttractionPictureRepository.findByLocationAttraction(attraction).stream()
                        .map(locationAttractionPicture -> LOCATION_ATTRACTION_IMAGE_BASE_URL + "/" + locationAttractionPicture.getId())
                        .collect(Collectors.toList())
        )).collect(Collectors.toList());
    }

    @Override
    public LocationAttractionDto getLocationAttractionById(long id) {
        LocationAttraction attraction = locationAttractionRepository.findById(id).orElseThrow(() -> new CustomServiceException(404, "Location attraction not found"));
        return new LocationAttractionDto(
                attraction.getId(),
                attraction.getName(),
                attraction.getDescription(),
                attraction.getTelephone(),
                attraction.getEmail(),
                attraction.getWebsite(),
                attraction.getVisibilityStatus(),
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                        .format(attraction.getUpdatedDateTime()),
                attraction.getLocation().getId(),
                locationAttractionPictureRepository.findByLocationAttraction(attraction).stream()
                        .map(locationAttractionPicture -> LOCATION_ATTRACTION_IMAGE_BASE_URL + "/" + locationAttractionPicture.getId())
                        .collect(Collectors.toList())
        );
    }

    @Override
    public List<LocationAttractionDto> getTopAttractions() {
        List<LocationAttraction> attractions = locationAttractionRepository.findTopAttractions();
        return attractions.stream().map(attraction -> new LocationAttractionDto(
                attraction.getId(),
                attraction.getName(),
                attraction.getDescription(),
                attraction.getTelephone(),
                attraction.getEmail(),
                attraction.getWebsite(),
                attraction.getVisibilityStatus(),
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                        .format(attraction.getUpdatedDateTime()),
                attraction.getLocation().getId(),
                locationAttractionPictureRepository.findByLocationAttraction(attraction).stream()
                        .map(locationAttractionPicture -> LOCATION_ATTRACTION_IMAGE_BASE_URL + "/" + locationAttractionPicture.getId())
                        .collect(Collectors.toList())
        )).collect(Collectors.toList());
    }

    @Override
    public List<LocationAttractionDto> getAllAttractionsByLocation(long locationId) {
        List<LocationAttraction> attractions = locationAttractionRepository.findLocationAttractionsByLocation(locationId);
        return attractions.stream().map(attraction -> new LocationAttractionDto(
                attraction.getId(),
                attraction.getName(),
                attraction.getDescription(),
                attraction.getTelephone(),
                attraction.getEmail(),
                attraction.getWebsite(),
                attraction.getVisibilityStatus(),
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                        .format(attraction.getUpdatedDateTime()),
                attraction.getLocation().getId(),
                locationAttractionPictureRepository.findByLocationAttraction(attraction).stream()
                        .map(locationAttractionPicture -> LOCATION_ATTRACTION_IMAGE_BASE_URL + "/" + locationAttractionPicture.getId())
                        .collect(Collectors.toList())
        )).collect(Collectors.toList());
    }

    @Override
    public List<LocationAttractionDto> getThreeAttractionsByLocation(long locationId) {
        List<LocationAttraction> attractions = locationAttractionRepository.findThreeAttractionsByLocation(locationId);
        return attractions.stream().map(attraction -> new LocationAttractionDto(
                attraction.getId(),
                attraction.getName(),
                attraction.getDescription(),
                attraction.getTelephone(),
                attraction.getEmail(),
                attraction.getWebsite(),
                attraction.getVisibilityStatus(),
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                        .format(attraction.getUpdatedDateTime()),
                attraction.getLocation().getId(),
                locationAttractionPictureRepository.findByLocationAttraction(attraction).stream()
                        .map(locationAttractionPicture -> LOCATION_ATTRACTION_IMAGE_BASE_URL + "/" + locationAttractionPicture.getId())
                        .collect(Collectors.toList())
        )).collect(Collectors.toList());
    }

    @Override
    public List<LocationAttractionDto> getRandThreeAttractionsByLocation(long locationId) {
        List<LocationAttraction> attractions = locationAttractionRepository.findRandThreeAttractionsByLocation(locationId);
        return attractions.stream().map(attraction -> new LocationAttractionDto(
                attraction.getId(),
                attraction.getName(),
                attraction.getDescription(),
                attraction.getTelephone(),
                attraction.getEmail(),
                attraction.getWebsite(),
                attraction.getVisibilityStatus(),
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                        .format(attraction.getUpdatedDateTime()),
                attraction.getLocation().getId(),
                locationAttractionPictureRepository.findByLocationAttraction(attraction).stream()
                        .map(locationAttractionPicture -> LOCATION_ATTRACTION_IMAGE_BASE_URL + "/" + locationAttractionPicture.getId())
                        .collect(Collectors.toList())
        )).collect(Collectors.toList());
    }
}
