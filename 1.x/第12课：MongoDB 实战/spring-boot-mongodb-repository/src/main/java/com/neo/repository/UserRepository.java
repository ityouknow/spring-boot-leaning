package com.neo.repository;

import com.neo.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, Long> {
    UserEntity findByUserName(String userName);
    Page<UserEntity> findAll(Pageable var1);
}