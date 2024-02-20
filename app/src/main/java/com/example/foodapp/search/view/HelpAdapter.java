package com.example.foodapp.search.view;

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
import com.example.foodapp.LoginActivity;
import com.example.foodapp.R;
import com.example.foodapp.meals.view.MealsAdapter;
import com.example.foodapp.meals.view.OnFavMealClickListener;
import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HelpAdapter extends RecyclerView.Adapter<HelpAdapter.HViewHolder> {
    private final static String TAG = "BeefAdapter";
    CircleImageView photo;
    Context context;
    private List<BeefMealsData> beefMealsList;
    NavController navController;

    private OnFavMealClickListener listener;

    SharedPreferences sharedPreferences;

    public HelpAdapter(Context context, List<BeefMealsData> beefMealsList, OnFavMealClickListener listener, SharedPreferences sharedPreferences) {
        this.context = context;
        this.beefMealsList = beefMealsList;
        this.listener = listener;
        sharedPreferences = context.getSharedPreferences(LoginActivity.PREFERENCE , Context.MODE_PRIVATE);

    }

    public HelpAdapter(Context context, List<BeefMealsData> beefMealsList) {
        this.context = context;
        this.beefMealsList = beefMealsList;
        sharedPreferences = context.getSharedPreferences(LoginActivity.PREFERENCE , Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public HViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.test,parent,false);
        HViewHolder beefViewHolder = new HViewHolder(view);
        Log.i(TAG,"<<<<<<< OnCreateViewHolder : BeefMeals >>>>>>>>>");
        return beefViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HViewHolder holder, int position) {
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

    public void setBeefMealsList(List<BeefMealsData> beefMealsList) {
        this.beefMealsList = beefMealsList;
    }

    @Override
    public int getItemCount() {
        return beefMealsList != null ? beefMealsList.size() : 0;
    }


    class HViewHolder extends RecyclerView.ViewHolder {

        private TextView textTitle;
        private Button insertFav;
        FrameLayout frameLayout;
        public HViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.catname);
            photo = itemView.findViewById(R.id.countryImg);
            insertFav = itemView.findViewById(R.id.countryName);
            frameLayout = itemView.findViewById(R.id.frameMeal);

        }
    }
}
