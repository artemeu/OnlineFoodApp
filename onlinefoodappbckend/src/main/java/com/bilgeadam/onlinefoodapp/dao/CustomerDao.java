package com.bilgeadam.onlinefoodapp.dao;

import com.bilgeadam.onlinefoodapp.domain.Customer;

import java.util.Optional;

public interface CustomerDao {

    Optional<Customer> findById (Long customerId);
    Optional<Customer> findByUsername(String username);

}
