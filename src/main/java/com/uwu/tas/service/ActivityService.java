package com.uwu.tas.service;

import com.uwu.tas.dto.configuration.ActivityDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActivityService {
    void saveActivity(ActivityDto activityDto);

    void updateActivity(ActivityDto activityDto);

    void changeActivityStatus(long id);

    List<ActivityDto> getAllActivities();

    void deleteActivity(long id);
}
