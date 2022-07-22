package com.uwu.tas.repository;

import com.uwu.tas.entity.RoomReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {
}
