package com.example.schedulemanagementapp.repository;

import com.example.schedulemanagementapp.dto.ScheduleDataResponseDto;
import com.example.schedulemanagementapp.dto.ScheduleResponseDto;
import com.example.schedulemanagementapp.entity.Paging;
import com.example.schedulemanagementapp.entity.Schedule;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JDBCTemplateScheduleRepository implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public JDBCTemplateScheduleRepository(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 데이터베이스에 일정 저장
    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {

        String name;
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("schedule_id");

        try {
            name = jdbcTemplate.queryForObject("select name from user where user_id = ?", String.class, 1);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 입력입니다.");
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", schedule.getTitle());
        parameters.put("user_id", schedule.getUserId());
        parameters.put("name", name);
        parameters.put("fixed_date", schedule.getFixedDate());
        parameters.put("registered_date", schedule.getRegisteredDate());
        parameters.put("password", schedule.getPassword());
        parameters.put("contents", schedule.getContents());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(key.longValue(),"Success", "일정 등록 성공.");
    }

    // 데이터베이스에서 전체 일정 출력
    @Override
    public List<ScheduleDataResponseDto> findAllSchedules() {

        return jdbcTemplate.query("select * from schedule", ScheduleRowMapper());
    }

    // id에 해당하는 데이터 출력
    @Override
    public ScheduleDataResponseDto findScheduleByIdOrElseThrow(Long id) {

        List<ScheduleDataResponseDto> result = jdbcTemplate.query("select * from schedule where schedule_id = ?", ScheduleRowMapper(),id);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " 해당 아이디가 존재하지 않습니다."));
    }

    // 입력한 패스워드 확인
    @Override
    public String returnPasswordById(Long id) {

        return jdbcTemplate.queryForObject("select password from schedule where schedule_id = ?", String.class, id);
    }

    // 데이터베이스에서 데이터 수정
    @Override
    public int updateSchedule(Long id, String name, String contents) {

        Date today = new Date();
        SimpleDateFormat todayString = new SimpleDateFormat("yyyy.MM.dd. HH:mm:ss");

        return jdbcTemplate.update("update schedule set name = ?, contents = ?, fixed_date = ? where schedule_id = ?", name, contents, todayString.format(today), id);
    }

    // 데이터베이스에서 데이터 삭제
    @Override
    public int deleteSchedule(Long id) {

        return jdbcTemplate.update("delete from schedule where schedule_id = ?", id);
    }

    // 페이지에 따라 데이터 출력
    @Override
    public List<ScheduleDataResponseDto> pagingList(int page, int size) {

        Paging paging = new Paging();
        paging.handlePaging(page, size);

        if (paging.getEndpage() > jdbcTemplate.queryForObject("select count(*) from schedule", Integer.class)) {

            return jdbcTemplate.query("select * from schedule limit ?, ?", ScheduleRowMapper(), paging.getEndpage(), 1);
        }

        return jdbcTemplate.query("select * from schedule limit ?, ?", ScheduleRowMapper(), paging.getStartpage(), paging.getSize());
    }

    // 데이터베이스에서 출력한 데이터 리스트를 알맞은 데이터 형으로 변환
    private RowMapper<ScheduleDataResponseDto> ScheduleRowMapper() {

        return new RowMapper<ScheduleDataResponseDto>() {

            @Override
            public ScheduleDataResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {

                return new ScheduleDataResponseDto(
                        rs.getLong("schedule_id"),
                        rs.getString("title"),
                        rs.getString("name"),
                        rs.getString("fixed_date"),
                        rs.getString("registered_date"),
                        rs.getString("contents")
                );
            }
        };
    }
}
