package com.example.schedulemanagementapp.repository;

import com.example.schedulemanagementapp.dto.ScheduleDataResponseDto;
import com.example.schedulemanagementapp.dto.UserDataResponseDto;
import com.example.schedulemanagementapp.dto.UserResponseDto;
import com.example.schedulemanagementapp.entity.User;

import java.util.List;

public interface UserRepository {

    UserResponseDto saveUser(User user);

    List<UserDataResponseDto> findAllUsers();

    UserDataResponseDto findUserByIdOrElseThrow(Long id);

    List<ScheduleDataResponseDto> findUserScheduleByIdOrElseThrow(Long id);

    int updateUser(Long id, String name, String email);

    int deleteUser(Long id);
}
