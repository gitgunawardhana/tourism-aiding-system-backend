package com.uwu.tas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private String name;
    private int age;

    public StudentDto(String name) {
        this.name = name;
    }
}
