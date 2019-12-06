package com.bilgeadam.onlinefoodapp.service;

import com.bilgeadam.onlinefoodapp.jwt.JwtCustomerDetails;
import com.bilgeadam.onlinefoodapp.jwt.JwtTokenUtil;
import com.bilgeadam.onlinefoodapp.jwt.JwtUserDetailsCustomerService;
import com.bilgeadam.onlinefoodapp.repo.CustomerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsCustomerService jwtUserDetailsCustomerService;

    public CustomerService(CustomerRepository customerRepository, JwtTokenUtil jwtTokenUtil, JwtUserDetailsCustomerService jwtUserDetailsCustomerService) {
        this.customerRepository = customerRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserDetailsCustomerService = jwtUserDetailsCustomerService;
    }

    public void addToCart(long id, String code) {
        customerRepository.addToCart(id, code);
    }

    public List checkCart(long id, String code) {
        return customerRepository.checkCart(id, code);
    }

    public void emptyCart(long id) {
        customerRepository.emptyCart(id);
    }

    public void deleteCartMeal(long id, String code){
        customerRepository.deleteCartMeal(id, code);
    }

    public long getId(HttpServletRequest req) {
        String username = getUsername(req);
        UserDetails userDetails = this.jwtUserDetailsCustomerService.loadUserByUsername(username);
        JwtCustomerDetails user = (JwtCustomerDetails) userDetails;
        return user.getId();
    }

    public String getUsername(HttpServletRequest req) {
        String token = req.getHeader(AUTHORIZATION).substring(7);
        return jwtTokenUtil.getUsernameFromToken(token);
    }
}
