package com.uwu.tas.service.impl;

import com.uwu.tas.dto.configuration.FacilityDto;
import com.uwu.tas.entity.Facility;
import com.uwu.tas.enums.VisibilityStatus;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.repository.FacilityRepository;
import com.uwu.tas.service.FacilityService;
import com.uwu.tas.util.Base64Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.uwu.tas.constant.TASConstants.Url.FACILITY_IMAGE_BASE_URL;

@Service
@RequiredArgsConstructor
public class FacilityServiceImpl implements FacilityService {

    private final FacilityRepository facilityRepository;
    private final Base64Handler base64Handler;

    @Override
    public void createFacility(FacilityDto facilityDto) {
        Optional<Facility> optionalFacility = facilityRepository.findByName(facilityDto.getName());
        if (optionalFacility.isPresent()) {
            throw new CustomServiceException("Facility already existing");
        }
        Facility facility = new Facility();
        facility.setName(facilityDto.getName());
        facility.setImage(base64Handler.getByteArrayFromBase64(facilityDto.getImage()));
        facility.setStatus(VisibilityStatus.VISIBLE);
        facilityRepository.save(facility);
    }

    @Override
    public void changeFacilityStatus(long id) {
        Facility facility = facilityRepository.findById(id).orElseThrow(() -> new CustomServiceException(404, "Facility not found"));
        VisibilityStatus status = VisibilityStatus.VISIBLE;
        if (facility.getStatus().equals(VisibilityStatus.VISIBLE)) status = VisibilityStatus.NOT_VISIBLE;
        facility.setStatus(status);
        facilityRepository.save(facility);
    }

    @Override
    public void updateFacility(FacilityDto facilityDto) {
        Facility facility = facilityRepository.findById(facilityDto.getId()).orElseThrow(() -> new CustomServiceException("Facility not found"));
        Optional<Facility> optionalFacility = facilityRepository.findByName(facilityDto.getName());
        if (optionalFacility.isPresent()) {
            if (facility.getId() != optionalFacility.get().getId()) {
                throw new CustomServiceException("Facility already existing under given name");
            }
        }
        facility.setName(facilityDto.getName());
        if (!facilityDto.getImage().startsWith("http")) {
            facility.setImage(base64Handler.getByteArrayFromBase64(facilityDto.getImage()));
        }
        facility.setStatus(VisibilityStatus.VISIBLE);
        facilityRepository.save(facility);
    }

    @Override
    public void deleteFacility(long id) {
        Facility facility = facilityRepository.findById(id).orElseThrow(() -> new CustomServiceException("Facility not found"));
        if (facility.getRoomFacilityDetails().size() > 0) {
            throw new CustomServiceException("Facility cannot be deleted. This facility is used under several accommodation rooms");
        }
        facilityRepository.delete(facility);
    }

    @Override
    public List<FacilityDto> getAllFacilities() {
        return facilityRepository.findAll().stream().map(facility ->
                new FacilityDto(facility.getId(),
                        facility.getName(),
                        FACILITY_IMAGE_BASE_URL + "/" + facility.getId(),
                        facility.getStatus())).collect(Collectors.toList());
    }
}
