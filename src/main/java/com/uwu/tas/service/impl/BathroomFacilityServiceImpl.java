package com.uwu.tas.service.impl;

import com.uwu.tas.dto.configuration.BathroomFacilityDto;
import com.uwu.tas.entity.BathroomFacility;
import com.uwu.tas.enums.VisibilityStatus;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.repository.BathroomFacilityRepository;
import com.uwu.tas.service.BathroomFacilityService;
import com.uwu.tas.util.Base64Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.uwu.tas.constant.TASConstants.Url.BATHROOM_FACILITY_IMAGE_BASE_URL;

@Service
@RequiredArgsConstructor
public class BathroomFacilityServiceImpl implements BathroomFacilityService {

    private final BathroomFacilityRepository bathroomFacilityRepository;
    private final Base64Handler base64Handler;

    @Override
    public void createBathroomFacility(BathroomFacilityDto facilityDto) {
        Optional<BathroomFacility> optionalFacility = bathroomFacilityRepository.findByName(facilityDto.getName());
        if (optionalFacility.isPresent()) {
            throw new CustomServiceException("Bathroom facility already existing");
        }
        BathroomFacility facility = new BathroomFacility();
        facility.setName(facilityDto.getName());
        facility.setImage(base64Handler.getByteArrayFromBase64(facilityDto.getImage()));
        facility.setStatus(VisibilityStatus.VISIBLE);
        bathroomFacilityRepository.save(facility);
    }

    @Override
    public void changeBathroomFacilityStatus(long id) {
        BathroomFacility facility = bathroomFacilityRepository.findById(id).orElseThrow(() -> new CustomServiceException(404, "Bathroom facility not found"));
        VisibilityStatus status = VisibilityStatus.VISIBLE;
        if (facility.getStatus().equals(VisibilityStatus.VISIBLE)) status = VisibilityStatus.NOT_VISIBLE;
        if (status.equals(VisibilityStatus.NOT_VISIBLE)) {
            if (facility.getRoomBathroomFacilityDetails().size() > 0) {
                throw new CustomServiceException("Bathroom facility status cannot be changed. This facility is used under several accommodation rooms");
            }
        }
        facility.setStatus(status);
        bathroomFacilityRepository.save(facility);
    }

    @Override
    public void updateBathroomFacility(BathroomFacilityDto facilityDto) {
        BathroomFacility facility = bathroomFacilityRepository.findById(facilityDto.getId()).orElseThrow(() -> new CustomServiceException("Bathroom facility not found"));
        Optional<BathroomFacility> optionalFacility = bathroomFacilityRepository.findByName(facilityDto.getName());
        if (optionalFacility.isPresent()) {
            if (facility.getId() != optionalFacility.get().getId()) {
                throw new CustomServiceException("Bathroom facility already existing under given name");
            }
        }
        facility.setName(facilityDto.getName());
        if (!facilityDto.getImage().startsWith("http")) {
            facility.setImage(base64Handler.getByteArrayFromBase64(facilityDto.getImage()));
        }
        bathroomFacilityRepository.save(facility);
    }

    @Override
    public void deleteBathroomFacility(long id) {
        BathroomFacility facility = bathroomFacilityRepository.findById(id).orElseThrow(() -> new CustomServiceException("Facility not found"));
        if (facility.getRoomBathroomFacilityDetails().size() > 0) {
            throw new CustomServiceException("Bathroom facility cannot be deleted. This facility is used under several accommodation rooms");
        }
        bathroomFacilityRepository.delete(facility);
    }

    @Override
    public List<BathroomFacilityDto> getAllBathroomFacilities() {
        return bathroomFacilityRepository.findAll().stream().map(facility ->
                new BathroomFacilityDto(facility.getId(),
                        facility.getName(),
                        BATHROOM_FACILITY_IMAGE_BASE_URL + "/" + facility.getId(),
                        facility.getStatus())).collect(Collectors.toList());
    }
}
