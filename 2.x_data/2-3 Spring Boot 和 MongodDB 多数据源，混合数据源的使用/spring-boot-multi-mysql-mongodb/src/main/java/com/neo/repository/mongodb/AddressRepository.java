package com.neo.repository.mongodb;

import com.neo.model.mongodb.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author neo
 */
public interface AddressRepository extends MongoRepository<Address, String> {
}
