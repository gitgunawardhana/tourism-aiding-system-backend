package com.uwu.tas.service.impl;

import com.uwu.tas.dto.configuration.ActivityDto;
import com.uwu.tas.entity.Activity;
import com.uwu.tas.enums.VisibilityStatus;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.repository.ActivityLocationDetailRepository;
import com.uwu.tas.repository.ActivityRepository;
import com.uwu.tas.service.ActivityService;
import com.uwu.tas.util.Base64Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.uwu.tas.constant.TASConstants.Url.ACTIVITY_IMAGE_BASE_URL;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityLocationDetailRepository activityLocationDetailRepository;

    private final Base64Handler base64Handler;

    @Override
    public void saveActivity(ActivityDto activityDto) {
        if (activityRepository.existsByActivityName(activityDto.getActivityName())) {
            throw new CustomServiceException(409, "Activity name already exists");
        }
        Activity activity = new Activity();
        activity.setActivityName(activityDto.getActivityName());
        activity.setImage(base64Handler.getByteArrayFromBase64(activityDto.getImage()));
        activity.setVisibilityStatus(VisibilityStatus.VISIBLE);
        activityRepository.save(activity);
    }

    @Override
    public void updateActivity(ActivityDto activityDto) {
        Activity activity = activityRepository.findById(activityDto.getId()).orElseThrow(() -> new CustomServiceException(404, "Activity not found"));
        activity.setActivityName(activityDto.getActivityName());
        if (!activityDto.getImage().startsWith("http")) {
            activity.setImage(base64Handler.getByteArrayFromBase64(activityDto.getImage()));
        }
        activity.setVisibilityStatus(activityDto.getStatus());
        activityRepository.save(activity);
    }

    @Override
    public void changeActivityStatus(long id, VisibilityStatus status) {
        Activity activity = activityRepository.findById(id).orElseThrow(() -> new CustomServiceException(404, "Activity not found"));
        activity.setVisibilityStatus(status);
        activityRepository.save(activity);
    }

    @Override
    public List<ActivityDto> getAllActivities() {
        return activityRepository.findAll().stream().map(activity -> new ActivityDto(
                activity.getId(),
                activity.getActivityName(),
                ACTIVITY_IMAGE_BASE_URL + "/" + activity.getId(),
                activity.getVisibilityStatus(),
                false
        )).collect(Collectors.toList());
    }

    @Override
    public void deleteActivity(long id) {
        Activity activity = activityRepository.findById(id).orElseThrow(() -> new CustomServiceException("Activity not found"));
        if (activity.getActivityLocationDetails().size() > 0) {
            throw new CustomServiceException("Activity cannot be deleted. This activity is used under several locations");
        }
        activityRepository.delete(activity);
    }
}
