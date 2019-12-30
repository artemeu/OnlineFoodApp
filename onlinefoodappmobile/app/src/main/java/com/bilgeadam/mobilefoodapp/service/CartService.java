package com.bilgeadam.mobilefoodapp.service;

import com.bilgeadam.mobilefoodapp.dto.Meal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CartService {
    @POST("/cart/{code}")
    Call<Void> addToCart(@Path("code") String code);

    @GET("/cart/all")
    Call<List<Meal>> getCartAll();

    @POST("/cart/submit")
    Call<Void>submitOrder();

    @DELETE("/cart/{code}")
    Call<Void> removeFromCart(@Path("code") String code);

}
