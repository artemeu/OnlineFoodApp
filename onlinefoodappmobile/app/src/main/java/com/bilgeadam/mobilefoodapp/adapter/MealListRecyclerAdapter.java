package com.bilgeadam.mobilefoodapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bilgeadam.mobilefoodapp.R;
import com.bilgeadam.mobilefoodapp.dto.Meal;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MealListRecyclerAdapter extends RecyclerView.Adapter<MealListRecyclerAdapter.MealViewHolder> {

    private List<Meal> mMealList;
    private Context context;

    public MealListRecyclerAdapter(Context context, ArrayList<Meal> meals) {
        this.context = context;
        this.mMealList = meals;
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

    public void setMealList(List<Meal> mMealList) {
        this.mMealList = mMealList;
    }

    class MealViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mealName, mealDescription;
        ImageView mealImage;

        MealViewHolder(View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.meal_image);
            mealName = itemView.findViewById(R.id.meal_name);
            mealDescription = itemView.findViewById(R.id.meal_description);

        }

        void setData(Meal meal, int position) {
            this.mealName.setText(meal.getName());
            this.mealDescription.setText(meal.getDetail());
            Glide.with(context)
                    .load(meal.getPhoto())
                    .centerCrop()
                    .into(mealImage);
        }

        @Override
        public void onClick(View v) {
        }
    }
}