package com.uwu.tas.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class RoomFacilityDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Facility facility;


}
