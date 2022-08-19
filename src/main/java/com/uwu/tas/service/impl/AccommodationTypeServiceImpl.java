package com.uwu.tas.service.impl;

import com.uwu.tas.dto.configuration.AccommodationTypeDto;
import com.uwu.tas.entity.AccommodationType;
import com.uwu.tas.enums.VisibilityStatus;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.repository.AccommodationTypeRepository;
import com.uwu.tas.service.AccommodationTypeService;
import com.uwu.tas.util.Base64Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.uwu.tas.constant.TASConstants.Url.ACCOMMODATION_TYPE_IMAGE_BASE_URL;

@Service
@RequiredArgsConstructor
public class AccommodationTypeServiceImpl implements AccommodationTypeService {

    private final AccommodationTypeRepository accommodationTypeRepository;
    private final Base64Handler base64Handler;

    @Override
    public void createAccommodationType(AccommodationTypeDto accommodationTypeDto) {
        Optional<AccommodationType> optionalAccommodationType = accommodationTypeRepository.findByName(accommodationTypeDto.getName());
        if (optionalAccommodationType.isPresent()) {
            throw new CustomServiceException("Accommodation type already existing");
        }
        AccommodationType accommodationType = new AccommodationType();
        accommodationType.setName(accommodationTypeDto.getName());
        accommodationType.setImage(base64Handler.getByteArrayFromBase64(accommodationTypeDto.getImage()));
        accommodationType.setStatus(VisibilityStatus.VISIBLE);
        accommodationTypeRepository.save(accommodationType);
    }

    @Override
    public void changeAccommodationTypeStatus(long id) {
        AccommodationType accommodationType = accommodationTypeRepository.findById(id).orElseThrow(() -> new CustomServiceException(404, "Accommodation type not found"));
        VisibilityStatus status = VisibilityStatus.VISIBLE;
        if (accommodationType.getStatus().equals(VisibilityStatus.VISIBLE)) status = VisibilityStatus.NOT_VISIBLE;
        accommodationType.setStatus(status);
        accommodationTypeRepository.save(accommodationType);
    }

    @Override
    public void updateAccommodationType(AccommodationTypeDto accommodationTypeDto) {
        AccommodationType accommodationType = accommodationTypeRepository.findById(accommodationTypeDto.getId()).orElseThrow(() -> new CustomServiceException("Accommodation type not found"));
        Optional<AccommodationType> optionalAccommodationType = accommodationTypeRepository.findByName(accommodationTypeDto.getName());
        if (optionalAccommodationType.isPresent()) {
            if (accommodationType.getId() != optionalAccommodationType.get().getId()) {
                throw new CustomServiceException("Accommodation type already existing under given name");
            }
        }
        accommodationType.setName(accommodationTypeDto.getName());
        if (!accommodationTypeDto.getImage().startsWith("http")) {
            accommodationType.setImage(base64Handler.getByteArrayFromBase64(accommodationTypeDto.getImage()));
        }
        accommodationType.setStatus(VisibilityStatus.VISIBLE);
        accommodationTypeRepository.save(accommodationType);
    }

    @Override
    public void deleteAccommodationType(long id) {
        AccommodationType accommodationType = accommodationTypeRepository.findById(id).orElseThrow(() -> new CustomServiceException("Accommodation type not found"));
        if (accommodationType.getAccommodations().size() > 0) {
            throw new CustomServiceException("Accommodation type cannot be deleted. There are accommodation registrations under this type");
        }
        accommodationTypeRepository.delete(accommodationType);
    }

    @Override
    public List<AccommodationTypeDto> getAllAccommodationTypes() {
        return accommodationTypeRepository.findAll().stream().map(accommodationType ->
                new AccommodationTypeDto(accommodationType.getId(),
                        accommodationType.getName(),
                        ACCOMMODATION_TYPE_IMAGE_BASE_URL + "/" + accommodationType.getId(),
                        accommodationType.getStatus())).collect(Collectors.toList());
    }
}
