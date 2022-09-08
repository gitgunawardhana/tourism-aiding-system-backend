package com.uwu.tas.repository;

import com.uwu.tas.entity.PublicUser;
import com.uwu.tas.entity.ReservationPackageDetail;
import com.uwu.tas.entity.RoomPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReservationPackageDetailRepository extends JpaRepository<ReservationPackageDetail, Long> {

    boolean existsByRoomPackageAndRoomReservation_PublicUserAndRoomReservation_ReservationStartDateAndRoomReservation_ReservationEndDate
            (RoomPackage roomPackage,
             PublicUser publicUser,
             LocalDate startDate,
             LocalDate endDate);
}
