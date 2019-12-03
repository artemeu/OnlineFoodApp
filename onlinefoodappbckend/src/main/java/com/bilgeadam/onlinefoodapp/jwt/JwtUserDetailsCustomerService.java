package com.bilgeadam.onlinefoodapp.jwt;

import com.bilgeadam.onlinefoodapp.domain.Customer;
import com.bilgeadam.onlinefoodapp.repo.CustomerServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailsCustomerService implements UserDetailsService {

    private final CustomerServiceImpl customerService;

    public JwtUserDetailsCustomerService(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Customer> userFromDB = customerService.findByUsername(username);
        JwtCustomerDetails jwtCustomerDetails = null;
        if (userFromDB.isPresent()) {
            jwtCustomerDetails= new JwtCustomerDetails(userFromDB.get());
        }else {
            throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
        }
        return jwtCustomerDetails;
    }
}



















