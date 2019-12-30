package com.bilgeadam.mobilefoodapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bilgeadam.mobilefoodapp.activity.LoginActivity;
import com.bilgeadam.mobilefoodapp.activity.MealDetailActivity;
import com.bilgeadam.mobilefoodapp.activity.MealMenuActivity;
import com.bilgeadam.mobilefoodapp.activity.OrderActivity;
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
    private SharedPreferences sharedPreferences;
    private boolean isLoggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        Button btnLogin = findViewById(R.id.loginbtn);
        if (sharedPreferences.contains("TOKEN")) {
            btnLogin.setText("Çıkış Yap");
        }
        if (!sharedPreferences.contains("TOKEN")) {
            btnLogin.setText("Giriş Yap");
        }

        configureSlider();
        getCampaigns();

        swipeRefresh = findViewById(R.id.swiperefresh);
        swipeRefresh.setOnRefreshListener(() -> {
            getCampaigns();
            swipeRefresh.setRefreshing(false);
        });
    }

    public void btnLogin(View v) {
        if (sharedPreferences.contains("TOKEN")) {
            sharedPreferences.edit().remove("TOKEN").apply();
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    public void showMealMenu(View view) {
        Intent intent = new Intent(this, MealMenuActivity.class);
        Bundle b = new Bundle();
        b.putBoolean("isMealMenu", true);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void showCart(View view) {
        if (sharedPreferences.contains("TOKEN")) {
            Intent intent = new Intent(this, MealMenuActivity.class);
            Bundle b = new Bundle();
            b.putBoolean("isCart", true);
            intent.putExtras(b);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    public void showOrders(View view) {
        if (sharedPreferences.contains("TOKEN")) {
            Intent intent = new Intent(this, OrderActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

    }

    private void configureSlider() {
        viewPager = findViewById(R.id.image_pager);
        imagePagerAdapter = new ImagePagerAdapter(this);
        viewPager.setAdapter(imagePagerAdapter);
        viewPager.setOnItemClickListener(position -> {
            if (!campaignMealList.isEmpty()) {
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
                campaignMealList = imagePagerAdapter.getCampaignMealList();
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

}
