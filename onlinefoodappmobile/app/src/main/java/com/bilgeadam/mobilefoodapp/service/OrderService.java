package com.bilgeadam.mobilefoodapp.service;

import com.bilgeadam.mobilefoodapp.dto.Meal;
import com.bilgeadam.mobilefoodapp.dto.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OrderService {

    @GET("/order/all")
    Call<List<Order>> getAllOrders();

    @GET("/order/{orderId}")
    Call<List<Meal>> getOrderDetail(@Path("orderId") long  orderId);

}
