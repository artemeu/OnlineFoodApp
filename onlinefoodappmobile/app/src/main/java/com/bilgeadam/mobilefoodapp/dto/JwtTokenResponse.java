package com.bilgeadam.mobilefoodapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class JwtTokenResponse implements Serializable {

    @JsonProperty("token")
    private String token;
    @JsonProperty("roles")
    private List roles;

    public JwtTokenResponse() {
    }

    public JwtTokenResponse(String token, List roles) {
        this.token = token;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List getRoles() {
        return roles;
    }

    public void setRoles(List roles) {
        this.roles = roles;
    }
}