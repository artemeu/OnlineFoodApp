package com.bilgeadam.onlinefoodapp.jwt.resource;

import java.io.Serializable;
import java.util.List;

public class JwtTokenResponse implements Serializable {

    private final String token;
    private final List roles;


    JwtTokenResponse(String token, List roles) {
        this.token = token;
        this.roles = roles;
    }

    public String getToken() {
        return this.token;
    }

    public List getRoles() {
        return this.roles;
    }
}