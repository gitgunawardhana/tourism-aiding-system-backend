package com.uwu.tas.entity;

import com.uwu.tas.enums.VisibilityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class LocationAttraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Lob
    private String description;

    private String telephone;
    private String email;
    private String website;

    @Enumerated(EnumType.STRING)
    private VisibilityStatus visibilityStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Location location;
}
