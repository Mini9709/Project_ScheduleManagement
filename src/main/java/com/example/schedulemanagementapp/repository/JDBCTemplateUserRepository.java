package com.example.schedulemanagementapp.repository;

import com.example.schedulemanagementapp.dto.ScheduleDataResponseDto;
import com.example.schedulemanagementapp.dto.UserDataResponseDto;
import com.example.schedulemanagementapp.dto.UserResponseDto;
import com.example.schedulemanagementapp.entity.User;
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
public class JDBCTemplateUserRepository implements UserRepository{

    private JdbcTemplate jdbcTemplate;

    public JDBCTemplateUserRepository(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 데이터베이스에 유저 등록
    @Override
    public UserResponseDto saveUser(User user) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("user").usingGeneratedKeyColumns("user_id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", user.getName());
        parameters.put("email", user.getEmail());
        parameters.put("fixed_date", user.getFixedDate());
        parameters.put("registered_date", user.getRegisteredDate());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new UserResponseDto(key.longValue(), "Success", "유저 등록 성공.");
    }

    // 데이터베이스에 전체 유저 출력
    @Override
    public List<UserDataResponseDto> findAllUsers() {

        return jdbcTemplate.query("select * from user", UserRowMapper());
    }

    // id에 해당하는 유저 출력
    @Override
    public UserDataResponseDto findUserByIdOrElseThrow(Long id) {

        List<UserDataResponseDto> result = jdbcTemplate.query("select * from user where user_id = ?", UserRowMapper(), id);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 아이디가 존재하지 않습니다."));
    }

    // id에 해당하는 유저의 일정 출력
    @Override
    public List<ScheduleDataResponseDto> findUserScheduleByIdOrElseThrow(Long id) {

        List<ScheduleDataResponseDto> result = jdbcTemplate.query("select * from schedule where user_id = ?", ScheduleRowMapper(), id);

        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 유저가 존재하지 않습니다.");
        }

        return result;
    }

    //// 데이터베이스에 데이터 수정
    @Override
    public int updateUser(Long id, String name, String email) {

        Date today = new Date();
        SimpleDateFormat todayString = new SimpleDateFormat("yyyy.MM.dd. HH:mm:ss");

        return jdbcTemplate.update("update user set name = ?, email = ?, fixed_date = ? where user_id = ?", name, email, todayString.format(today),id);
    }

    // 데이터베이스에 데이터 삭제
    @Override
    public int deleteUser(Long id) {

        jdbcTemplate.update("delete from schedule where user_id = ?", id);

        return jdbcTemplate.update("delete from user where user_id = ?", id);
    }

    private RowMapper<UserDataResponseDto> UserRowMapper() {

        return new RowMapper<UserDataResponseDto>() {

            @Override
            public UserDataResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {

                return new UserDataResponseDto(
                        rs.getLong("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("fixed_date"),
                        rs.getString("registered_date")
                );
            }
        };
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
