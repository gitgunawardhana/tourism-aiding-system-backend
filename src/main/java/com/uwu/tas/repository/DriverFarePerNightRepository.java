package com.uwu.tas.repository;

import com.uwu.tas.entity.DriverFarePerNight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverFarePerNightRepository extends JpaRepository<DriverFarePerNight, Long> {
}
