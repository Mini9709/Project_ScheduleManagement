package com.example.schedulemanagementapp.controller;

import com.example.schedulemanagementapp.dto.*;
import com.example.schedulemanagementapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    // 유저 등록
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto dto) {

        return new ResponseEntity<>(userService.saveUser(dto), HttpStatus.CREATED);
    }

    // 유저 전체 조회
    @GetMapping
    public ResponseEntity<List<UserDataResponseDto>> findAllUsers() {

        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    // 선택 유저 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserDataResponseDto> findUserById(@PathVariable Long id) {

        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    // 선택한 유저의 일정 조회
    @GetMapping("/{id}/schedules")
    public ResponseEntity<List<ScheduleDataResponseDto>> findUserScheduleById(@PathVariable Long id) {

        return new ResponseEntity<>(userService.findUserScheduleById(id), HttpStatus.OK);
    }

    // 유저 정보 수정
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequestDto dto
    ) {

        return new ResponseEntity<>(userService.updateUser(id, dto.getName(), dto.getEmail()), HttpStatus.OK);
    }

    // 유저 정보 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDto> deleteUser(@PathVariable Long id) {

        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }
}
