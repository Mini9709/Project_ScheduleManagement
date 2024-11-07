package com.example.schedulemanagementapp.service;

import com.example.schedulemanagementapp.dto.ScheduleDataResponseDto;
import com.example.schedulemanagementapp.dto.ScheduleRequestDto;
import com.example.schedulemanagementapp.dto.ScheduleResponseDto;
import com.example.schedulemanagementapp.entity.Schedule;
import com.example.schedulemanagementapp.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {

        this.scheduleRepository = scheduleRepository;
    }

    // 일정 작성
    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {

        Schedule schedule = new Schedule(
                dto.getTitle(),
                dto.getUserId(),
                dto.getPassword(),
                dto.getContents()
        );

        return scheduleRepository.saveSchedule(schedule);
    }

    // 전체 일정 조회
    @Override
    public List<ScheduleDataResponseDto> findAllSchedules() {

        return scheduleRepository.findAllSchedules();
    }

    // 선택 일정 조회
    @Override
    public ScheduleDataResponseDto findScheduleById(Long id) {

        return scheduleRepository.findScheduleByIdOrElseThrow(id);
    }

    // 일정 수정
    @Override
    public ScheduleResponseDto updateSchedule(Long id, String name, String contents, String password) {

        // 비밀번호가 틀렸을 경우, 실패 메시지 출력
        if (!password.equals(scheduleRepository.returnPasswordById(id))) {
            return new ScheduleResponseDto(id, "Failed", "비밀번호가 틀렸습니다.");
        }

        // 입력값이 제대로 오지 않았을 경우, 오류 메시지 출력
        if(name == null || contents == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이름이나 내용이 입력되지 않았습니다.");
        }

        int updateRow = scheduleRepository.updateSchedule(id, name, contents);

        // 해당 아이디가 존재하지 않을 경우, 오류 메시지 출력
        if (updateRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 아이디가 존재하지 않습니다.");
        }

        return new ScheduleResponseDto(id,"Success", "일정 수정 성공.");
    }

    // 일정 삭제
    @Override
    public ScheduleResponseDto deleteSchedule(Long id, String password) {

        // 비밀번호가 틀렸을 경우, 실패 메시지 출력
        if (!password.equals(scheduleRepository.returnPasswordById(id))) {
            return new ScheduleResponseDto(id, "Failed", "비밀번호가 틀렸습니다.");
        }

        int deleteRow = scheduleRepository.deleteSchedule(id);

        // 해당 아이디가 존재하지 않을 경우, 오류 메시지 출력
        if (deleteRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 아이디가 존재하지 않습니다.");
        }

        return new ScheduleResponseDto(id, "Success", "일정 삭제 성공.");
    }
}
