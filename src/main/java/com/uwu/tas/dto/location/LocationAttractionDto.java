package com.uwu.tas.dto.location;

import com.uwu.tas.enums.VisibilityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocationAttractionDto {

    private long id;
    private String name;
    private String description;
    private String telephone;
    private String email;
    private String website;
    private VisibilityStatus visibilityStatus;
    private long locationId;
    private List<String> locationAttractionPictures;
}
