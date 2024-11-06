package com.example.schedulemanagementapp.repository;

import com.example.schedulemanagementapp.dto.ScheduleDataResponseDto;
import com.example.schedulemanagementapp.dto.ScheduleResponseDto;
import com.example.schedulemanagementapp.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);
    List<ScheduleDataResponseDto> findAllSchedules();
    ScheduleDataResponseDto findScheduleByIdOrElseThrow(Long id);
}
