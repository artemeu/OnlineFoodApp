package com.bilgeadam.mobilefoodapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.bilgeadam.mobilefoodapp.activity.MealDetailActivity;
import com.bilgeadam.mobilefoodapp.activity.MealMenuActivity;
import com.bilgeadam.mobilefoodapp.adapter.ImagePagerAdapter;
import com.bilgeadam.mobilefoodapp.custom.ClickableViewPager;
import com.bilgeadam.mobilefoodapp.dto.JwtTokenRequest;
import com.bilgeadam.mobilefoodapp.dto.JwtTokenResponse;
import com.bilgeadam.mobilefoodapp.dto.Meal;
import com.bilgeadam.mobilefoodapp.service.AuthenticateService;
import com.bilgeadam.mobilefoodapp.service.MealDataService;
import com.bilgeadam.mobilefoodapp.utililty.AppUtils;
import com.bilgeadam.mobilefoodapp.utililty.RetrofitClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ImagePagerAdapter imagePagerAdapter;
    private CircleIndicator circleIndicator;
    private ClickableViewPager viewPager;
    private List<Meal> campaignMealList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        authenticate();
        configureSlider();

        swipeRefresh = findViewById(R.id.swiperefresh);
        swipeRefresh.setOnRefreshListener(() -> {
            getCampaigns();
            swipeRefresh.setRefreshing(false);
        });
    }

    public void showMealMenu(View view) {
        Intent intent = new Intent(this, MealMenuActivity.class);
        startActivity(intent);
    }

    private void configureSlider() {
        viewPager = findViewById(R.id.image_pager);
        imagePagerAdapter = new ImagePagerAdapter(this);
        viewPager.setAdapter(imagePagerAdapter);
        viewPager.setOnItemClickListener(position -> {
            if(!campaignMealList.isEmpty()) {
                Intent intent = new Intent(getApplicationContext(), MealDetailActivity.class);
                intent.putExtra("meal", campaignMealList.get(position));
                startActivity(intent);
            }
        });
        AppUtils.automaticSlide(viewPager, imagePagerAdapter);
        circleIndicator = findViewById(R.id.circle);
        circleIndicator.setViewPager(viewPager);
    }

    private void getCampaigns() {
        MealDataService mealDataService = RetrofitClient.getRetrofitInstance(this).create(MealDataService.class);
        mealDataService.getCampaigns().enqueue(new Callback<List<Meal>>() {
            @Override
            public void onResponse(@NonNull Call<List<Meal>> call, @NonNull Response<List<Meal>> response) {
                imagePagerAdapter.setCampaignMealList(response.body());
                campaignMealList= imagePagerAdapter.getCampaignMealList();
                imagePagerAdapter.notifyDataSetChanged();
                circleIndicator = findViewById(R.id.circle);
                circleIndicator.setViewPager(viewPager);
            }

            @Override
            public void onFailure(@NonNull Call<List<Meal>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void authenticate() {
        AuthenticateService authenticateService = RetrofitClient.getRetrofitInstance(this).create(AuthenticateService.class);
        authenticateService.authenticate(new JwtTokenRequest("mesutcan", "123")).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String responseString = response.body().string();
                    JwtTokenResponse jwtTokenResponse = new ObjectMapper().readValue(responseString, JwtTokenResponse.class);
                    SharedPreferences sharedPref = getSharedPreferences(
                            getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                    sharedPref.edit().putString("TOKEN", jwtTokenResponse.getToken()).apply();
                    getCampaigns();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
