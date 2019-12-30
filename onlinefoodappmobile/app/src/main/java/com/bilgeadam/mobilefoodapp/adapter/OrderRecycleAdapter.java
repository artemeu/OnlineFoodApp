package com.bilgeadam.mobilefoodapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bilgeadam.mobilefoodapp.R;
import com.bilgeadam.mobilefoodapp.activity.MealMenuActivity;
import com.bilgeadam.mobilefoodapp.activity.OrderActivity;
import com.bilgeadam.mobilefoodapp.dto.Order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderRecycleAdapter extends RecyclerView.Adapter<OrderRecycleAdapter.OrderViewHolder> {

    private Context context;
    private List<Order> orderList;

    public OrderRecycleAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        holder.setData(orderList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView
                orderId,
                orderPrice,
                orderStatus,
                orderDate;
        Button btnOrderDetails;

        OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.order_id);
            orderPrice = itemView.findViewById(R.id.order_price);
            orderStatus = itemView.findViewById(R.id.order_status);
            orderDate = itemView.findViewById(R.id.order_date);
            btnOrderDetails = itemView.findViewById(R.id.btn_order_details);

            btnOrderDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Order order = orderList.get(position);
                    //TODO : getMealByOrderId
                    Intent intent = new Intent(context, MealMenuActivity.class);
                    Bundle b = new Bundle();
                    b.putBoolean("isOrderDetail", true);
                    b.putLong("orderId", order.getOrderId());
                    intent.putExtras(b);
                    context.startActivity(intent);
                }
            });
        }


        void setData(Order order, int position) {
            this.orderId.setText("Sipariş Id: " + order.getOrderId());
            this.orderPrice.setText("Sipariş Toplam Tutarı: " + order.getPrice() + ".00 TL");
            if (order.getStatus() == null) {
                this.orderStatus.setText("Sipariş Durumu: Hazırlanıyor ");
            } else if (order.getStatus() == true) {
                this.orderStatus.setText("Sipariş Durumu: Teslimat Aşamasında ");
            } else {
                this.orderStatus.setText("Sipariş Durumu: Teslim Edildi ");
            }
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String orderDate = formatter.format(order.getPlacementDate());
            this.orderDate.setText("Sipariş Tarihi: " + orderDate);
        }
    }
}
