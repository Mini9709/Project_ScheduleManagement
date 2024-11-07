package com.example.schedulemanagementapp.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class UserRequestDto {

    private Long userId;
    private String name;
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;
}
