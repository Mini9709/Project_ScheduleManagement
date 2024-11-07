package com.example.schedulemanagementapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.text.SimpleDateFormat;
import java.util.*;

@Getter
@AllArgsConstructor
public class User {

    private Long userId;
    private String name;
    private String email;
    private String fixedDate;
    private String registeredDate;

    public User(String name, String email) {
        this.name = name;
        this.email = email;

        Date today = new Date();
        SimpleDateFormat todayString = new SimpleDateFormat("yyyy.MM.dd. HH:mm:ss");
        this.fixedDate = todayString.format(today);
        this.registeredDate = todayString.format(today);
    }
}
