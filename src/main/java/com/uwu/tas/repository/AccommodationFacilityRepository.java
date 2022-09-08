package com.uwu.tas.repository;

import com.uwu.tas.entity.Accommodation;
import com.uwu.tas.entity.AccommodationFacility;
import com.uwu.tas.enums.HousingFacilityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationFacilityRepository extends JpaRepository<AccommodationFacility, Long> {

    List<AccommodationFacility> findAllByAccommodationAndHousingFacility_Type(Accommodation accommodation, HousingFacilityType type);

    List<AccommodationFacility> findAllByAccommodation(Accommodation accommodation);
}
