package com.bilgeadam.onlinefoodapp.jwt;

import com.bilgeadam.onlinefoodapp.domain.Customer;
import com.bilgeadam.onlinefoodapp.repo.CustomerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailsCustomerService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public JwtUserDetailsCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Customer> userFromDB = customerRepository.findByUsername(username);
        JwtCustomerDetails jwtCustomerDetails = null;
        if (userFromDB.isPresent()) {
            jwtCustomerDetails= new JwtCustomerDetails(userFromDB.get());
        }else {
            throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
        }
        return jwtCustomerDetails;
    }
}



















