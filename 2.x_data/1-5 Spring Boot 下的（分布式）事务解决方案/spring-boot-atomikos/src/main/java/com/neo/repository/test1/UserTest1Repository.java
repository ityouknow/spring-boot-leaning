package com.neo.repository.test1;

import com.neo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTest1Repository extends JpaRepository<User, Long> {
}