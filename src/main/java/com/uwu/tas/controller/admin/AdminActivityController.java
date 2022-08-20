package com.uwu.tas.controller.admin;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.configuration.ActivityDto;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.service.ActivityService;
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

    @PostMapping(value = "")
    public ResponseEntity saveActivity(@RequestBody ActivityDto activityDto) {
        try {
            activityService.saveActivity(activityDto);
            return ResponseEntity.ok(new CommonResponse<>(true, "Activity saved successfully!"));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @PutMapping(value = "")
    public ResponseEntity updateActivity(@RequestBody ActivityDto activityDto) {
        try {
            activityService.updateActivity(activityDto);
            return ResponseEntity.ok(new CommonResponse<>(true, "Activity updated successfully!"));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity changeActivityStatus(@PathVariable(value = "id") long id) {
        try {
            activityService.changeActivityStatus(id);
            return ResponseEntity.ok(new CommonResponse<>(true, "Activity status changed successfully!"));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteActivity(@PathVariable(value = "id") long id) {
        try {
            activityService.deleteActivity(id);
            return ResponseEntity.ok(new CommonResponse<>(true, "Activity deleted successfully!"));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }

    @GetMapping(value = "")
    public ResponseEntity getAllActivities() {
        try {
            List<ActivityDto> allActivities = activityService.getAllActivities();
            return ResponseEntity.ok(new CommonResponse<>(true, allActivities));
        } catch (CustomServiceException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, e.getMessage()));
        } catch (Exception ee) {
            ee.printStackTrace();
            return ResponseEntity.ok(new CommonResponse<>(false, "Something went wrong!"));
        }
    }
}
