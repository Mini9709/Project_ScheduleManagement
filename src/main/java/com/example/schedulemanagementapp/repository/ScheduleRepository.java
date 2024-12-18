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

    String returnPasswordById(Long id);

    int updateSchedule(Long id, String name, String contents);

    int deleteSchedule(Long id);

    List<ScheduleDataResponseDto> pagingList(int page, int size);
}
