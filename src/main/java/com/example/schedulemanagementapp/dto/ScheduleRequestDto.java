package com.example.schedulemanagementapp.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    private String title;
    private Long userId;
    private String name;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
    @Max(value = 200, message = "200자 내로 적어주세요.")@NotNull(message = "할 일을 입력해주세요.")
    private String contents;

}
