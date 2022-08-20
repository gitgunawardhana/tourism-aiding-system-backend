package com.uwu.tas.repository;

import com.uwu.tas.entity.BathroomFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BathroomFacilityRepository extends JpaRepository<BathroomFacility, Long> {

    Optional<BathroomFacility> findByName(String name);
}
