package com.uwu.tas.dto.location;

import com.uwu.tas.enums.VisibilityStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LocationAttractionDto {

    private long id;
    private String name;
    private String description;
    private String telephone;
    private String email;
    private String website;
    private VisibilityStatus visibilityStatus;
    private String modifiedDateTime;
    private long locationId;
    private List<String> locationAttractionPictures;
}
