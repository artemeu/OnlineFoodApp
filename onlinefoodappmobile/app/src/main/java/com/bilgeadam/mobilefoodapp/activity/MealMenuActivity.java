package com.bilgeadam.mobilefoodapp.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bilgeadam.mobilefoodapp.R;
import com.bilgeadam.mobilefoodapp.adapter.MealListRecyclerAdapter;
import com.bilgeadam.mobilefoodapp.dto.Meal;
import com.bilgeadam.mobilefoodapp.service.MealDataService;
import com.bilgeadam.mobilefoodapp.utililty.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealMenuActivity extends AppCompatActivity {

    private MealListRecyclerAdapter mMealAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_menu);

        //meal list
        ArrayList<Meal> mealList = new ArrayList<>();
        /*mealList.add(new Meal("txt","Mantı","desc"));
        mealList.add(new Meal("txt","Çorba","desc"));
        mealList.add(new Meal("txt","Kavurma","desc"));*/

        //yemek fotolarını değiştir
        // https://i4.hurimg.com/i/hurriyet/75/1500x844/5cdfce0667b0a90adcf9aec5.jpg
        // https://i4.hurimg.com/i/hurriyet/75/1500x844/5d95ed0ac03c0e1c5093199e.jpg
        // https://i4.hurimg.com/i/hurriyet/75/750x422/5d4fca332269a20f5c723a58.jpg
        //mealList.add(new Meal("https://i4.hurimg.com/i/hurriyet/75/1500x844/5cdfce0667b0a90adcf9aec5.jpg", "Mantı", "desc"));
        //mealList.add(new Meal("https://i4.hurimg.com/i/hurriyet/75/1500x844/5d95ed0ac03c0e1c5093199e.jpg", "Çorba", "desc"));
        //mealList.add(new Meal("https://cdn.yemek.com/mncrop/313/280/uploads/2016/09/sac-kavurma-ytk-site.jpg", "Kavurma", "desc"));

        //listview
        /*final ListView listview = findViewById(R.id.listview);
        final MealListViewAdapter adapter = new MealListViewAdapter(this, mealList);
        listview.setAdapter(adapter);*/

        //recyclerview
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        RecyclerView mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mMealAdapter = new MealListRecyclerAdapter(this, mealList);
        mRecyclerView.setAdapter(mMealAdapter);

        getMeals();
    }

    private void getMeals() {
        MealDataService mealDataService = RetrofitClient.getRetrofitInstance(this).create(MealDataService.class);
        mealDataService.getMeals().enqueue(new Callback<List<Meal>>() {
            @Override
            public void onResponse(Call<List<Meal>> call, Response<List<Meal>> response) {
                mMealAdapter.setMealList(response.body());
                mMealAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Meal>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
