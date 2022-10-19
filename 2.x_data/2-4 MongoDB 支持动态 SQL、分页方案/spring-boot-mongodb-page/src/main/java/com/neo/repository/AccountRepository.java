package com.neo.repository;

import com.neo.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, Long> {
    Page<Account> findByAccountStatus(String accountStatus, Pageable pageable);
}