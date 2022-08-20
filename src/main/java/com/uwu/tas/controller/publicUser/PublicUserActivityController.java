package com.uwu.tas.controller.publicUser;

import com.uwu.tas.dto.CommonResponse;
import com.uwu.tas.dto.configuration.ActivityDto;
import com.uwu.tas.exception.CustomServiceException;
import com.uwu.tas.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/public-user/activity")
@CrossOrigin
public class PublicUserActivityController {

    private final ActivityService activityService;

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
