package com.uwu.tas.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {

    private boolean success;
    private String message;
    private T body;

    public CommonResponse(boolean success, T body) {
        this.success = success;
        this.body = body;
    }

    public CommonResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
