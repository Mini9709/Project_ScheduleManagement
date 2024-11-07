package com.example.schedulemanagementapp.service;

import com.example.schedulemanagementapp.dto.*;
import com.example.schedulemanagementapp.entity.User;
import com.example.schedulemanagementapp.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    // 유저 등록
    @Override
    public UserResponseDto saveUser(UserRequestDto dto) {

        User user = new User(
                dto.getName(),
                dto.getEmail()
        );

        return userRepository.saveUser(user);
    }

    // 유저 전체 조회
    @Override
    public List<UserDataResponseDto> findAllUsers() {

        return userRepository.findAllUsers();
    }

    // 선택 유저 조회
    @Override
    public UserDataResponseDto findUserById(Long id) {

        return userRepository.findUserByIdOrElseThrow(id);
    }

    @Override
    public List<ScheduleDataResponseDto> findUserScheduleById(Long id) {

        return userRepository.findUserScheduleByIdOrElseThrow(id);
    }

    // 유저 정보 수정
    @Override
    public UserResponseDto updateUser(Long id, String name, String email) {

        if (name == null || email == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이름이나 이메일이 입력되지 않았습니다.");
        }

        int updateRow = userRepository.updateUser(id, name, email);

        if (updateRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 아이디가 존재하지 않습니다.");
        }
        return new UserResponseDto(id, "Success", "유저 정보 수정 성공.");
    }

    // 유저 정보 삭제
    @Override
    public UserResponseDto deleteUser(Long id) {

        int deleteRow = userRepository.deleteUser(id);

        if (deleteRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 아이디가 존재하지 않습니다.");
        }

        return new UserResponseDto(id, "Success", "유저 정보 삭제 성공.");
    }
}
