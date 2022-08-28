package com.uwu.tas.service;

import com.uwu.tas.dto.accommodation.AccommodationDto;
import com.uwu.tas.entity.Vendor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccommodationService {
    List<AccommodationDto> getAccommodationsForVendor(Vendor vendor);

    AccommodationDto getAccommodationById(long id);
}
