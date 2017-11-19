package com.neo.model.repository.primary;

import com.neo.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author neo
 */
public interface PrimaryRepository extends MongoRepository<UserEntity, String> {
}
