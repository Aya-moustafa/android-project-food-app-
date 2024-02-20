package com.example.foodapp.search.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.R;
import com.example.foodapp.meals.view.BeefMealsView;
import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FilterResultAdapter extends RecyclerView.Adapter<FilterResultAdapter.FilterResultViewHolder>{

    private final static String TAG = "FilterResultAdapter" ;
    Context context;

    CircleImageView photoo;

    List<BeefMealsData> beefMealsViewList;
    @NonNull
    @Override
    public FilterResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.test,parent,false);
        FilterResultViewHolder filterResultViewHolder = new FilterResultViewHolder(view);
        Log.i(TAG,"<<<<<<< OnCreateViewHolder : BeefMeals >>>>>>>>>");
        return filterResultViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FilterResultViewHolder holder, int position) {
        BeefMealsData beefMealsData = beefMealsViewList.get(position);
        holder.mealName.setText(beefMealsData.getStrMeal());
        Glide.with(context)
                .load(beefMealsViewList.get(position).getStrMealThumb())
                .into(photoo);


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class FilterResultViewHolder extends RecyclerView.ViewHolder {
        private TextView mealName;
        private Button insertFavo;
        FrameLayout frameLayout;

        public View layout;
        public FilterResultViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.catname);
            photoo = itemView.findViewById(R.id.countryImg);
            insertFavo = itemView.findViewById(R.id.countryName);
            frameLayout = itemView.findViewById(R.id.frameMeal);
        }
    }
}
