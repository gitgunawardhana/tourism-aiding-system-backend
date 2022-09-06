package com.uwu.tas.dto.accommodation;

import com.uwu.tas.dto.configuration.FacilityDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomDto {

    private long accommodationId;
    private String roomName;
    private String description;
    private String size;
    private int totalRoomCount;
    private long viewId;
    private List<String> images;
    private List<Long> roomFacilityIds;
    private List<Long> bathroomFacilityIds;

    private long id;
    private int availableRoomCount;
    private String view;
    private List<FacilityDto> roomFacilities;
    private List<FacilityDto> bathroomFacilities;
}
