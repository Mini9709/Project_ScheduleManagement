package com.example.schedulemanagementapp.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class ScheduleRequestDto {

    private String title;
    private Long userId;
    private String name;
    private String password;
    private String contents;

}
