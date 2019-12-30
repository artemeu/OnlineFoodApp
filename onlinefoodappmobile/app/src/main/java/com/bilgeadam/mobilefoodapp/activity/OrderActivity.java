package com.bilgeadam.mobilefoodapp.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bilgeadam.mobilefoodapp.R;

import com.bilgeadam.mobilefoodapp.adapter.OrderRecycleAdapter;
import com.bilgeadam.mobilefoodapp.dto.Order;
import com.bilgeadam.mobilefoodapp.service.OrderService;
import com.bilgeadam.mobilefoodapp.utililty.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {

    private OrderRecycleAdapter orderAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list);

        //order list
        ArrayList<Order> orderList = new ArrayList<>();

        //recyclerview
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        RecyclerView oRecyclerView = findViewById(R.id.orderrecyclerview);
        oRecyclerView.setLayoutManager(mLayoutManager);
        oRecyclerView.setItemAnimator(new DefaultItemAnimator());
        orderAdapter = new OrderRecycleAdapter(this, orderList);
        oRecyclerView.setAdapter(orderAdapter);

        getOrders();
    }


    private void getOrders() {
        OrderService orderService = RetrofitClient.getRetrofitInstance(this).create(OrderService.class);
        orderService.getAllOrders().enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                orderAdapter.setOrderList(response.body());
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
