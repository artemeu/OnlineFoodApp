package com.bilgeadam.mobilefoodapp.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.bilgeadam.mobilefoodapp.R;
import com.bilgeadam.mobilefoodapp.dto.Meal;
import com.bumptech.glide.Glide;

public class MealDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

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
}
