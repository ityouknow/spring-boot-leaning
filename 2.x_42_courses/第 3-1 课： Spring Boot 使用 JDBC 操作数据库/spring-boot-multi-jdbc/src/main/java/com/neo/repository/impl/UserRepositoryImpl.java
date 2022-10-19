package com.neo.repository.impl;

import com.neo.model.User;
import com.neo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private JdbcTemplate primaryJdbcTemplate;

    @Override
    public int save(User user,JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate == null){
            jdbcTemplate= primaryJdbcTemplate;
        }
        return jdbcTemplate.update("INSERT INTO users(name, password, age) values(?, ?, ?)",
              user.getName(), user.getPassword(), user.getAge());
    }

    @Override
    public int update(User user,JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate==null){
            jdbcTemplate= primaryJdbcTemplate;
        }
        return jdbcTemplate.update("UPDATE users SET name = ? , password = ? , age = ? WHERE id=?",
               user.getName(), user.getPassword(), user.getAge(), user.getId());
    }

    @Override
    public int delete(long id,JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate==null){
            jdbcTemplate= primaryJdbcTemplate;
        }
        return jdbcTemplate.update("DELETE FROM users where id = ? ",id);

    }

    @Override
    public User findById(long id,JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate==null){
            jdbcTemplate= primaryJdbcTemplate;
        }
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id=?", new Object[] { id }, new BeanPropertyRowMapper<User>(User.class));
    }

    @Override
    public List<User> findALL(JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate==null){
            jdbcTemplate= primaryJdbcTemplate;
        }
        return jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
        // return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper(User.class));
    }

    class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setAge(rs.getInt("age"));
            return user;
        }
    }

}
