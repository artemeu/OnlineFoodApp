package com.bilgeadam.mobilefoodapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bilgeadam.mobilefoodapp.R;
import com.bilgeadam.mobilefoodapp.adapter.MealListRecyclerAdapter;
import com.bilgeadam.mobilefoodapp.dto.Meal;
import com.bilgeadam.mobilefoodapp.service.CartService;
import com.bilgeadam.mobilefoodapp.service.MealDataService;
import com.bilgeadam.mobilefoodapp.service.OrderService;
import com.bilgeadam.mobilefoodapp.utililty.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealMenuActivity extends AppCompatActivity {

    private MealListRecyclerAdapter mMealAdapter;
    private boolean isMealMenu;
    private boolean isCart;
    private boolean isOrderDetail;
    private long orderId;
    private Button btnSubmitOrder;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_menu);
        Bundle b = getIntent().getExtras();
        isMealMenu = b.getBoolean("isMealMenu");
        isCart = b.getBoolean("isCart");
        orderId = b.getLong("orderId");
        isOrderDetail = b.getBoolean("isOrderDetail");

        context = this;

        btnSubmitOrder = (Button) findViewById(R.id.submit_order);
        btnSubmitOrder.setVisibility(View.GONE);

        //meal list
        ArrayList<Meal> mealList = new ArrayList<>();

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

    public void btnSubmitOrder(View v) {
        CartService cartService = RetrofitClient.getRetrofitInstance(this).create(CartService.class);
        cartService.submitOrder().enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() != 401 && response.code() == 200) {
                    isMealMenu = false;
                    getMeals();
                    Toast.makeText(getApplicationContext(), "Sipariş Oluşturuldu", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(context, LoginActivity.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Bilinmeyen Bir Hata Oluştu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getMealMenu() {
        MealDataService mealDataService = RetrofitClient.getRetrofitInstance(this).create(MealDataService.class);
        mealDataService.getMeals().enqueue(new Callback<List<Meal>>() {
            @Override
            public void onResponse(Call<List<Meal>> call, Response<List<Meal>> response) {
                setTitle("Yemek Menüsü");
                mMealAdapter.setMealList(response.body(), false, true, false);
                mMealAdapter.notifyDataSetChanged();
                btnSubmitOrder.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Meal>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getCart() {
        CartService cartService = RetrofitClient.getRetrofitInstance(this).create(CartService.class);
        cartService.getCartAll().enqueue(new Callback<List<Meal>>() {
            @Override
            public void onResponse(Call<List<Meal>> call, Response<List<Meal>> response) {
                if (response.code() != 401 && response.code() == 200) {
                    setTitle("Sepetim");
                    mMealAdapter.setMealList(response.body(), true, false, false);
                    mMealAdapter.notifyDataSetChanged();
                    btnSubmitOrder.setVisibility(View.VISIBLE);
                    if (response.body().size() == 0) {
                        btnSubmitOrder.setEnabled(false);
                        btnSubmitOrder.setText("Sepetinizde Ürün Bulunmuyor");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Meal>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getOrderDetail() {
        OrderService orderService = RetrofitClient.getRetrofitInstance(this).create(OrderService.class);
        orderService.getOrderDetail(orderId).enqueue(new Callback<List<Meal>>() {

            @Override
            public void onResponse(Call<List<Meal>> call, Response<List<Meal>> response) {
                setTitle("Sipariş Detayı");
                mMealAdapter.setMealList(response.body(), false, false, true);
                mMealAdapter.notifyDataSetChanged();
                btnSubmitOrder.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Meal>> call, Throwable t) {

            }
        });
    }

    private void getMeals() {
        if (isMealMenu) {
            getMealMenu();
        }
        if (isCart) {
            getCart();
        }
        if (isOrderDetail) {
            getOrderDetail();
        }
    }

}
