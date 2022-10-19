package com.neo.repository.mysql;

import com.neo.model.mysql.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author neo
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
