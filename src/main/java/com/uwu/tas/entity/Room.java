package com.uwu.tas.entity;


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
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Lob
    private String description;
    private String size;
    private int availableCount;
    private int totalCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Accommodation accommodation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private View view;

    @OneToMany(mappedBy = "room")
    private List<RoomPicture> roomPictures;

    @OneToMany(mappedBy = "room")
    private List<RoomPackage> roomPackages;

    @OneToMany(mappedBy = "room")
    private List<RoomFacilityDetail> roomFacilityDetails;

    @OneToMany(mappedBy = "room")
    private List<RoomBathroomFacilityDetail> roomBathroomFacilityDetails;
}






