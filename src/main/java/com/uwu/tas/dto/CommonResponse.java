package com.uwu.tas.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {

    private boolean success;
    private T body;
}
