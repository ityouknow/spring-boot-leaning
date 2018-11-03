package com.neo.repository;

import com.neo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface UserRepository  {

    int save(User user,JdbcTemplate jdbcTemplate);

    int update(User user,JdbcTemplate jdbcTemplate);

    int delete(long id,JdbcTemplate jdbcTemplate);

    List<User> findALL(JdbcTemplate jdbcTemplate);

    User findById(long id,JdbcTemplate jdbcTemplate);
}