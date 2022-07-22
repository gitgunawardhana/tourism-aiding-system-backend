package com.uwu.tas.repository;

import com.uwu.tas.entity.ReservationVehicleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationVehicleDetailRepository extends JpaRepository<ReservationVehicleDetail, Long> {
}
