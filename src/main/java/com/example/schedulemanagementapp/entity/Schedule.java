package com.example.schedulemanagementapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@AllArgsConstructor
public class Schedule {

    private Long scheduleId;
    private Long userId;
    private String title;
    private String fixedDate;
    private String registeredDate;
    private String name;
    private String password;
    private String contents;

    public Schedule(String title, Long userId, String password, String contents) {

        this.title = title;
        this.userId = userId;
        this.password = password;
        this.contents = contents;

        Date today = new Date();
        SimpleDateFormat todayString = new SimpleDateFormat("yyyy.MM.dd. HH:mm:ss");
        this.fixedDate = todayString.format(today);
        this.registeredDate = todayString.format(today);
    }
}
