package com.example.schedulemanagementapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class User {

    private Long userId;
    private String name;
    private String email;
    private Date fixedDate;
    private Date registeredDate;
}
