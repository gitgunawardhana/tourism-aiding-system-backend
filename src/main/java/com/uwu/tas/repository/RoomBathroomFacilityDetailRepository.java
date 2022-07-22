package com.uwu.tas.repository;

import com.uwu.tas.entity.RoomBathroomFacilityDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomBathroomFacilityDetailRepository extends JpaRepository<RoomBathroomFacilityDetail, Long> {
}
