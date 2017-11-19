package com.neo.model.repository.secondary;

import com.neo.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author neo
 */
public interface SecondaryRepository extends MongoRepository<UserEntity, String> {
}
