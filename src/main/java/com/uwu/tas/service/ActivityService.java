package com.uwu.tas.service;

import com.uwu.tas.dto.activity.ActivityDto;
import com.uwu.tas.enums.VisibilityStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActivityService {
    void saveActivity(ActivityDto activityDto);

    void updateActivity(ActivityDto activityDto);

    void changeActivityStatus(long id, VisibilityStatus status);

    List<ActivityDto> getAllActivities();
}
