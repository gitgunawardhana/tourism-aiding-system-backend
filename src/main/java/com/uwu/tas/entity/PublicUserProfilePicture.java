package com.uwu.tas.entity;

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
public class PublicUserProfilePicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private double id;

    private byte[] image;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private PublicUser publicUser;


}
