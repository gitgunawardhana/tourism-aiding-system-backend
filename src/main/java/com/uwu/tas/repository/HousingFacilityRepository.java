package com.uwu.tas.repository;

import com.uwu.tas.entity.HousingFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HousingFacilityRepository extends JpaRepository<HousingFacility, Long> {
}
