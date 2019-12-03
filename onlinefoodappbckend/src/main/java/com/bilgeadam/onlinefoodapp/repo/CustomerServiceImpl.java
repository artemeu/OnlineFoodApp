package com.bilgeadam.onlinefoodapp.repo;

import com.bilgeadam.onlinefoodapp.dao.CustomerDaoImpl;
import com.bilgeadam.onlinefoodapp.domain.Customer;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDaoImpl customerDao;

    public CustomerServiceImpl(CustomerDaoImpl customerDao) {
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
