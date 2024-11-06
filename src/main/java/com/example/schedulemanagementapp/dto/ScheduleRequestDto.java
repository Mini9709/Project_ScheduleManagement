package com.example.schedulemanagementapp.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class ScheduleRequestDto {

    private String title;
    private String name;
    private Date fixedDate;
    private Date registerdDate;
    private String password;
    private String contents;

}
