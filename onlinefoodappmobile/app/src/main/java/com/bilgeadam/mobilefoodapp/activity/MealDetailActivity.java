package com.bilgeadam.mobilefoodapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.bilgeadam.mobilefoodapp.R;
import com.bilgeadam.mobilefoodapp.dto.Meal;
import com.bilgeadam.mobilefoodapp.service.CartService;
import com.bilgeadam.mobilefoodapp.utililty.RetrofitClient;
import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MealDetailActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        context = this;

        ImageView mealImage = findViewById(R.id.meal_image);
        TextView mealDetail = findViewById(R.id.meal_detail);
        TextView mealPrice = findViewById(R.id.meal_price);

        Meal meal = (Meal) getIntent().getSerializableExtra("meal");
        if(meal!=null){
            Glide.with(this)
                    .load(meal.getPhoto())
                    .placeholder(getResources().getDrawable(R.color.cardview_dark_background,null))
                    .into(mealImage);
            mealDetail.setText(meal.getDetail());
            mealPrice.setText(String.valueOf(meal.getPrice()));
        }
    }

    public void addToCart(View view) {
        Meal meal = (Meal) getIntent().getSerializableExtra("meal");
        CartService cartService = RetrofitClient.getRetrofitInstance(this).create(CartService.class);
        try {
            cartService.addToCart(meal.getCode()).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.code() != 401 && response.code() == 200) {
                        Toast.makeText(getApplicationContext(), "Sepete Eklendi", Toast.LENGTH_SHORT).show();
                        System.out.println("SUCCESS");
                    } else {
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                    }

                }
                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    System.out.println("FAILED");
                }
            });
        }catch (Exception e){
            System.out.println("GELEN HATA " + e.toString());
        }
    }

}
