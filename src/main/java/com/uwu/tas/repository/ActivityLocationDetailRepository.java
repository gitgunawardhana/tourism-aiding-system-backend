package com.uwu.tas.repository;

import com.uwu.tas.entity.ActivityLocationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityLocationDetailRepository extends JpaRepository<ActivityLocationDetail, Long> {
}
