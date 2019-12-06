package com.bilgeadam.onlinefoodapp.domain;

public class Cart {
    private String code;

    public Cart(String code) {
        this.code = code;
    }

    public Cart() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
