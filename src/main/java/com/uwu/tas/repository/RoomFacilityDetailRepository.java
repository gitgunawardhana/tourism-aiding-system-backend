package com.uwu.tas.repository;

import com.uwu.tas.entity.RoomFacilityDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomFacilityDetailRepository extends JpaRepository<RoomFacilityDetail, Long> {
}
