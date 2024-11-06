package com.example.schedulemanagementapp.controller;

import com.example.schedulemanagementapp.dto.ScheduleDataResponseDto;
import com.example.schedulemanagementapp.dto.ScheduleRequestDto;
import com.example.schedulemanagementapp.dto.ScheduleResponseDto;
import com.example.schedulemanagementapp.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto) {

        return new ResponseEntity<>(scheduleService.saveSchedule(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleDataResponseDto>> findAllSchedules() {

        return new ResponseEntity<>(scheduleService.findAllSchedules(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDataResponseDto> findScheduleById(@PathVariable Long id) {

        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto dto
    ) {

        return new ResponseEntity<>(scheduleService.updateSchedule(id, dto.getName(), dto.getContents(), dto.getPassword()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> deleterSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto dto
    ) {

        return new ResponseEntity<>(scheduleService.deleteSchedule(id, dto.getPassword()), HttpStatus.OK);
    }

}
