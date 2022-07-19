package com.uwu.tas.repository;

import com.uwu.tas.entity.ReservationPackageDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationPackageDetailRepository extends JpaRepository<ReservationPackageDetail, Long> {
}
