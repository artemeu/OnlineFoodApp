package com.bilgeadam.onlinefoodapp.repo;

import com.bilgeadam.onlinefoodapp.dao.CustomerDao;
import com.bilgeadam.onlinefoodapp.domain.Customer;

import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public Optional<Customer> findById(Long customerId) {
        return customerDao.findById(customerId);
    }

    @Override
    public Optional<Customer> findByUsername(String username) {
        return customerDao.findByUsername(username);
    }
}
