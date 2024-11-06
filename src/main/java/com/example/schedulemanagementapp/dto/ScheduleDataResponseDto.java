package com.example.schedulemanagementapp.dto;

import com.example.schedulemanagementapp.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleDataResponseDto{

    private String title;
    private String name;
    private String fixedDate;
    private String registerdDate;
    private String contents;

    public ScheduleDataResponseDto(Schedule schedule) {
        this.title = schedule.getTitle();
        this.name = schedule.getName();
        this.fixedDate = schedule.getFixedDate();
        this.registerdDate = schedule.getRegisteredDate();
        this.contents = schedule.getContents();
    }

}