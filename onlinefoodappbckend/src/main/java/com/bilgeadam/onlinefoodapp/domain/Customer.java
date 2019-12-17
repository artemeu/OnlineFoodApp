package com.bilgeadam.onlinefoodapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "CUSTOMER")
public class Customer {

    @Id
    @Column(name = "CUSTOMER_ID")
    private long customerId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "USERNAME")
    @JsonIgnore
    private String username;
    @Column(name = "PASSWORD")
    @JsonIgnore
    private String password;

    @Column(name = "ADDRESS")
    private String address;

    @ManyToMany(mappedBy = "customers")
    private Set<Meal> meals;

    @OneToMany(mappedBy="customer")
    private Set<Order> orders;

    public Customer() {
    }

    public Customer(long customerId, String name, String surname, String username, String password, String address, Set<Meal> meals, Set<Order> orders) {
        this.customerId = customerId;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.address = address;
        this.meals = meals;
        this.orders = orders;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Meal> getMeals() {
        return meals;
    }

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
