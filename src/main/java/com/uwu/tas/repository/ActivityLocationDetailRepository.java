package com.uwu.tas.repository;

import com.uwu.tas.entity.Activity;
import com.uwu.tas.entity.ActivityLocationDetail;
import com.uwu.tas.entity.Location;
import com.uwu.tas.enums.VisibilityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityLocationDetailRepository extends JpaRepository<ActivityLocationDetail, Long> {

    List<ActivityLocationDetail> findByLocationAndActivity_VisibilityStatus(Location location, VisibilityStatus status);

    boolean existsByLocationAndActivity(Location location, Activity activity);

    long countByLocation(Location location);
}
