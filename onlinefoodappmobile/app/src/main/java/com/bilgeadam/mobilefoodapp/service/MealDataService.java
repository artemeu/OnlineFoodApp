package com.bilgeadam.mobilefoodapp.service;

import com.bilgeadam.mobilefoodapp.dto.Meal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealDataService {

    @GET("/meals/all")
    Call<List<Meal>> getMeals();

    @GET("/meals/campaigns")
    Call<List<Meal>> getCampaigns();
}