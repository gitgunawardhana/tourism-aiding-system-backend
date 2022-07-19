package com.uwu.tas.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private double longitude;
    private double latitude;

    @Lob
    private String description;

    private int minimum_spending_days;

    @CreationTimestamp
    private LocalDateTime createdDateTime;

    @UpdateTimestamp
    private LocalDateTime updatedDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Province province;

    @OneToMany(mappedBy = "location")
    private List<LocationPicture> locationPictures;


}
