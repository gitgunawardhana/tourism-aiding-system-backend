package com.uwu.tas.entity;


import com.uwu.tas.enums.VisibilityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class BathroomFacility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Lob
    private byte[] image;

    @Enumerated(EnumType.STRING)
    private VisibilityStatus status;

    @OneToMany(mappedBy = "bathroomFacility")
    private List<RoomBathroomFacilityDetail> roomBathroomFacilityDetails;

}
