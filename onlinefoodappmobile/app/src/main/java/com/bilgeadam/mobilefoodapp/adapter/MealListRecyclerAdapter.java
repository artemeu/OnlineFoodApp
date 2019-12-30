package com.bilgeadam.mobilefoodapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bilgeadam.mobilefoodapp.R;
import com.bilgeadam.mobilefoodapp.activity.LoginActivity;
import com.bilgeadam.mobilefoodapp.activity.MealMenuActivity;
import com.bilgeadam.mobilefoodapp.dto.Meal;
import com.bilgeadam.mobilefoodapp.service.CartService;
import com.bilgeadam.mobilefoodapp.utililty.RetrofitClient;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealListRecyclerAdapter extends RecyclerView.Adapter<MealListRecyclerAdapter.MealViewHolder> {

    private List<Meal> mMealList;
    private Context context;
    private MealListRecyclerAdapter adapter;
    private boolean _isCart;
    private boolean _isMealMenu;
    private boolean _isOrderDetail;

    public MealListRecyclerAdapter(Context context, ArrayList<Meal> meals) {
        this.context = context;
        this.mMealList = meals;
        this.adapter = this;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_meal, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MealViewHolder holder, int position) {
        holder.setData(mMealList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mMealList.size();
    }

    public void setMealList(List<Meal> mMealList, boolean isCart, boolean isMealMenu, boolean isOrderDetail) {
        this.mMealList = mMealList;
        this._isCart = isCart;
        this._isMealMenu = isMealMenu;
        this._isOrderDetail = isOrderDetail;
    }

    class MealViewHolder extends RecyclerView.ViewHolder {

        TextView mealName, mealDescription;
        ImageView mealImage;
        Button btnAddRemove;

        @SuppressLint("CutPasteId")
        MealViewHolder(View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.meal_image);
            mealName = itemView.findViewById(R.id.meal_name);
            mealDescription = itemView.findViewById(R.id.meal_description);
            btnAddRemove = itemView.findViewById(R.id.btnAdd_Remove);

            btnAddRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Meal meal = mMealList.get(position);
                    String mCode = meal.getCode();

                    if (_isCart) {
                        removeFromCart(mCode, position);
                    } else {
                        addToCart(mCode);
                    }
                }
            });

        }

        void removeFromCart(String code, int position) {
            CartService cartService = RetrofitClient.getRetrofitInstance(context).create(CartService.class);
            try {
                cartService.removeFromCart(code).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        System.out.println("Removed From Cart");
                        List<Meal> meals = mMealList;
                        meals.remove(position);
                        setMealList(meals, true, false, false);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(context, "Ürün Çıktarıldı", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        System.out.println("FAILED TO REMOVE FROM CART");
                    }
                });
            } catch (Exception e) {
                System.out.println("GELEN HATA " + e.toString());
            }
        }

        void addToCart(String code) {
            CartService cartService = RetrofitClient.getRetrofitInstance(context).create(CartService.class);
            try {
                cartService.addToCart(code).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() != 401 && response.code() == 200) {
                            System.out.println("SUCCESS");
                            Toast.makeText(context, "Sepete Eklendi", Toast.LENGTH_SHORT).show();
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
            } catch (Exception e) {
                System.out.println("GELEN HATA " + e.toString());
            }
        }

        void setData(Meal meal, int position) {
            this.mealName.setText(meal.getName());
            this.mealDescription.setText(meal.getDetail());
            if (_isCart) {
                this.btnAddRemove.setText("Sil");
            }
            if (_isMealMenu) {
                this.btnAddRemove.setText("Sepete Ekle");
            }
            if (_isOrderDetail) {
                this.btnAddRemove.setEnabled(false);
                this.btnAddRemove.setText(String.valueOf(meal.getPrice()) + ".00 TL");
            }

            Glide.with(context)
                    .load(meal.getPhoto())
                    .centerCrop()
                    .into(mealImage);
        }
    }
}