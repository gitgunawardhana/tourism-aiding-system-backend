package com.uwu.tas.repository;

import com.uwu.tas.entity.VehicleReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleReservationRepository extends JpaRepository<VehicleReservation, Long> {
}
