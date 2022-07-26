package com.uwu.tas.controller.admin;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.activity.ActivityDto;
import com.uwu.tas.enums.VisibilityStatus;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.repository.ActivityRepository;
import com.uwu.tas.service.ActivityService;
import com.uwu.tas.util.Base64Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/admin/activity")
@CrossOrigin
public class AdminActivityController {

    private final ActivityService activityService;

    private final ActivityRepository activityRepository;
    private final Base64Handler base64Handler;

    @PostMapping(value = "")
    public ResponseEntity saveActivity(@RequestBody ActivityDto activityDto) {
        try {
            activityService.saveActivity(activityDto);
            return ResponseEntity.ok(new CommonResponse<>(true, "Activity saved successfully!"));
        } catch (CustomServiceException e) {
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        }
    }

    @PutMapping(value = "")
    public ResponseEntity updateActivity(@RequestBody ActivityDto activityDto) {
        try {
            activityService.updateActivity(activityDto);
            return ResponseEntity.ok(new CommonResponse<>(true, "Activity updated successfully!"));
        } catch (CustomServiceException e) {
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity changeActivityStatus(@PathVariable(value = "id") long id, @RequestParam(value = "status") VisibilityStatus status) {
        try {
            activityService.changeActivityStatus(id, status);
            return ResponseEntity.ok(new CommonResponse<>(true, "Activity status changed successfully!"));
        } catch (CustomServiceException e) {
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        }
    }

    @GetMapping(value = "")
    public ResponseEntity getAllActivities() {
        try {
            List<ActivityDto> allActivities = activityService.getAllActivities();
            return ResponseEntity.ok(new CommonResponse<>(true, allActivities));
        } catch (CustomServiceException e) {
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        }
    }
}
