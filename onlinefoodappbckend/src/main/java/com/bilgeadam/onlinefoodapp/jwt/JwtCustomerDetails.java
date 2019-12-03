package com.bilgeadam.onlinefoodapp.jwt;

import com.bilgeadam.onlinefoodapp.domain.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.awt.*;
import java.util.Collection;

public class JwtCustomerDetails implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;

    public JwtCustomerDetails(Customer customer) {
        this.id = customer.getCustomerId();
        this.username = customer.getUsername();
        this.password = customer.getPassword();
    }

    public JwtCustomerDetails(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
