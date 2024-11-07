package com.example.schedulemanagementapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    private Long userId;
    private String result;
    private String message;

    public UserResponseDto(String result, String message) {
        this.result = result;
        this.message = message;
    }

}
