package com.example.schedulemanagementapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long scheduleId;
    // private Long userId;
    private String result;
    private String message;
}
