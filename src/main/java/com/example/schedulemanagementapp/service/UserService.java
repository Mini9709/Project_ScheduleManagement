package com.example.schedulemanagementapp.service;

import com.example.schedulemanagementapp.dto.*;

import java.util.List;

public interface UserService {

    UserResponseDto saveUser(UserRequestDto dto);

    List<UserDataResponseDto> findAllUsers();

    UserDataResponseDto findUserById(Long id);

    List<ScheduleDataResponseDto> findUserScheduleById(Long id);

    UserResponseDto updateUser(Long id, String name, String email);

    UserResponseDto deleteUser(Long id);
}
