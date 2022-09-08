package com.uwu.tas.repository;

import com.uwu.tas.entity.RoomPackage;
import com.uwu.tas.entity.RoomReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {

    @Query(value = "SELECT COUNT(rpd.id) FROM ReservationPackageDetail rpd " +
            "JOIN rpd.roomReservation rr " +
            "WHERE rpd.roomPackage = :roomPackage " +
            "AND (:startDate BETWEEN rr.reservationStartDate AND rr.reservationEndDate) " +
            "AND (:endDate BETWEEN rr.reservationStartDate AND rr.reservationEndDate) ")
    long checkPackageAvailability(@Param("roomPackage") RoomPackage roomPackage, @Param("startDate") LocalDate startDate,
                                  @Param("endDate") LocalDate endDate);
}
