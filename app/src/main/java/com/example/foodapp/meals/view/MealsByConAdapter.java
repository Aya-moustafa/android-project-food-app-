package com.example.foodapp.meals.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.R;
import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MealsByConAdapter extends RecyclerView.Adapter<MealsByConAdapter.ConViewHolder> {
    private final static String TAG = "BeefAdapter";
    CircleImageView photo;
    Context context;
    private List<BeefMealsData> beefMealsList;
    NavController navController;

    private OnFavMealClickListener listener;

    SharedPreferences sharedPreferences;

    @NonNull
    @Override
    public ConViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.test,parent,false);
        ConViewHolder beefViewHolder = new ConViewHolder(view);
        Log.i(TAG,"<<<<<<< OnCreateViewHolder : BeefMeals >>>>>>>>>");
        return beefViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ConViewHolder holder, int position) {
        String userEmail = sharedPreferences.getString("user_email","");

        if(beefMealsList != null) {
            BeefMealsData beefMeal = beefMealsList.get(position);
            beefMeal.setUserEmail(userEmail);
            holder.textTitle.setText(beefMeal.getStrMeal());
            Glide.with(context)
                    .load(beefMealsList.get(position).getStrMealThumb())
                    .into(photo);

            holder.insertFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClickListener(beefMeal);
                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClickListener(view,beefMeal);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return beefMealsList.size();
    }

    class ConViewHolder extends RecyclerView.ViewHolder {

        private TextView textTitle;
        private Button insertFav;
        FrameLayout frameLayout;
        public ConViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.catname);
            photo = itemView.findViewById(R.id.countryImg);
            insertFav = itemView.findViewById(R.id.countryName);
            frameLayout = itemView.findViewById(R.id.frameMeal);

        }
    }
}
