package com.example.schedulemanagementapp.dto;

import com.example.schedulemanagementapp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDataResponseDto {

    private Long userId;
    private String name;
    private String email;
    private String fixedDate;
    private String registeredDate;

    public UserDataResponseDto(User user) {
        this.userId = user.getUserId();
    }
}
