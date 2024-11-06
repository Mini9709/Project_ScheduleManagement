package com.example.schedulemanagementapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long scheduleId;
    private String result;
    private String message;

    public ScheduleResponseDto(String result, String message) {
        this.result = result;
        this.message = message;
    }
}
