package com.example.schedulemanagementapp.service;

import com.example.schedulemanagementapp.dto.ScheduleDataResponseDto;
import com.example.schedulemanagementapp.dto.ScheduleRequestDto;
import com.example.schedulemanagementapp.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);

    List<ScheduleDataResponseDto> findAllSchedules();

    ScheduleDataResponseDto findScheduleById(Long id);

    ScheduleResponseDto updateSchedule(Long id, String name, String contents, String password);

    ScheduleResponseDto deleteSchedule(Long id, String password);

    List<ScheduleDataResponseDto> pagingList(int page, int size);
}
