package com.example.schedulemanagementapp.repository;

import com.example.schedulemanagementapp.dto.ScheduleDataResponseDto;
import com.example.schedulemanagementapp.dto.ScheduleResponseDto;
import com.example.schedulemanagementapp.entity.Schedule;
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

    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("schedule_id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", schedule.getTitle());
        parameters.put("name", schedule.getName());
        parameters.put("fixed_date", schedule.getFixedDate());
        parameters.put("registered_date", schedule.getRegisteredDate());
        parameters.put("password", schedule.getPassword());
        parameters.put("contents", schedule.getContents());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return new ScheduleResponseDto(key.longValue(),"Success", "일정 등록 성공.");
    }

    @Override
    public List<ScheduleDataResponseDto> findAllSchedules() {
        return jdbcTemplate.query("select * from schedule", ScheduleRowMapper());
    }

    @Override
    public ScheduleDataResponseDto findScheduleByIdOrElseThrow(Long id) {
        List<ScheduleDataResponseDto> result = jdbcTemplate.query("select * from schedule where schedule_id = ?", ScheduleRowMapper(),id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " 해당 아이디가 존재하지 않습니다."));
    }

    @Override
    public String returnPasswordById(Long id) {
        return jdbcTemplate.queryForObject("select password from schedule where schedule_id = ?", String.class, id);
    }

    @Override
    public int updateSchedule(Long id, String name, String contents) {
        Date today = new Date();
        SimpleDateFormat todayString = new SimpleDateFormat("yyyy.MM.dd. HH:mm:ss");
        return jdbcTemplate.update("update schedule set name = ?, contents = ?, fixed_date = ? where schedule_id = ?", name, contents, todayString.format(today), id);
    }

    @Override
    public int deleteSchedule(Long id) {
        return jdbcTemplate.update("delete from schedule where schedule_id = ?", id);
    }


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
