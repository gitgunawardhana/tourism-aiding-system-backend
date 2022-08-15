package com.uwu.tas.dto.activity;

import com.uwu.tas.enums.VisibilityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ActivityDto {
    private long id;
    private String activityName;
    private String image;
    private VisibilityStatus status;
    private boolean checked;
}
