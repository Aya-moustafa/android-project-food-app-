package com.example.foodapp.weekPlan.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.R;
import com.example.foodapp.favorites.view.FavoriateAdapter;
import com.example.foodapp.meals.view.OnFavMealClickListener;
import com.example.foodapp.model.MealPlan.MealWithDate;
import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;

import java.util.List;

public class WeekPlanAdapter extends RecyclerView.Adapter<WeekPlanAdapter.WeekViewHolder>{

    ImageView photo;
    Context context;

    List<MealWithDate> weekMealsList;
    private onWeekPlanMealListener Listener ;

    public WeekPlanAdapter(Context context, List<MealWithDate> weekMealsList, onWeekPlanMealListener listener) {
        this.context = context;
        this.weekMealsList = weekMealsList;
        Listener = listener;
    }

    @NonNull
    @Override
    public WeekViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.daily_meal_item,parent,false);
        WeekViewHolder weekViewHolder =new WeekViewHolder(view);
        Log.i("TAG","<<<<<<< OnCreateViewHolder : Favoriate Products >>>>>>>>>");
        return weekViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeekViewHolder holder, int position) {
        if(weekMealsList != null) {
            MealWithDate mealWithDate = weekMealsList.get(position);
            holder.mealTitle.setText(mealWithDate.getStrMeal());
            Glide.with(context)
                    .load(weekMealsList.get(position).getStrMealThumb())
                    .into(photo);

            holder.remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Listener.onWeekPlanClickListener(mealWithDate);
                }
            });
        }

    }

    public void setWeekMealsList(List<MealWithDate> weekMealsList) {
        this.weekMealsList = weekMealsList;
    }

    @Override
    public int getItemCount() {
        return weekMealsList.size();
    }

    class WeekViewHolder extends RecyclerView.ViewHolder {

        private TextView mealTitle , mealDate;
        CardView cardView;
        Button remove;
        ImageView removeIcon;


        public WeekViewHolder(@NonNull View itemView) {
            super(itemView);
            mealTitle = itemView.findViewById(R.id.dailytxt);
            mealDate = itemView.findViewById(R.id.datepickertxt);
            photo = itemView.findViewById(R.id.dailymealimg);
            remove = itemView.findViewById(R.id.insert);
            removeIcon = itemView.findViewById(R.id.insertdelete);
            removeIcon.setImageResource(R.drawable.remove_icon);
            // add.setPointerIcon();  to change the icon
            cardView = itemView.findViewById(R.id.cardviewDaily);


        }
    }
}
