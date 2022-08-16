package com.uwu.tas.repository;

import com.uwu.tas.entity.Activity;
import com.uwu.tas.enums.VisibilityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    boolean existsByActivityName(String activityName);

    List<Activity> findByVisibilityStatus(VisibilityStatus status);

}
