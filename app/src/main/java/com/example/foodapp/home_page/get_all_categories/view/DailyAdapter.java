package com.example.foodapp.home_page.get_all_categories.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.R;
import com.example.foodapp.model.Meal.MealDetails;
import com.example.foodapp.model.MealPlan.MealWithDate;
import com.example.foodapp.weekPlan.view.WeekPlanAdapter;

import java.io.BufferedReader;
import java.util.List;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.DailyViewHolder>{
    ImageView photo;
    Context context;

    List<MealDetails> dailyMealsList;

    public DailyAdapter(Context context, List<MealDetails> dailyMealsList) {
        this.context = context;
        this.dailyMealsList = dailyMealsList;
    }

    @NonNull
    @Override
    public DailyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.daily_meal_item,parent,false);
        DailyViewHolder dailyViewHolder =new DailyViewHolder(view);
        Log.i("TAG","<<<<<<< OnCreateViewHolder : Favoriate Products >>>>>>>>>");
        return dailyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DailyViewHolder holder, int position) {
        MealDetails mealDetails = dailyMealsList.get(position);
            holder.textTitle.setText(mealDetails.getStrMeal());
            Glide.with(context)
                    .load(dailyMealsList.get(position).getStrMealThumb())
                    .into(photo);

    }

    public void setDailyMealsList(List<MealDetails> dailyMealsList) {
        this.dailyMealsList = dailyMealsList;
    }

    @Override
    public int getItemCount() {
        return dailyMealsList.size();
    }

    class DailyViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitle;
        CardView cardView;
        Button insert;
        public DailyViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.dailymealimg);
            textTitle = itemView.findViewById(R.id.dailytxt);
            cardView = itemView.findViewById(R.id.cardviewDaily);
            insert = itemView.findViewById(R.id.insert);
        }


    }
}
