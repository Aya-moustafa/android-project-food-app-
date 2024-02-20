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
import com.example.foodapp.meals.view.MealsAdapter;
import com.example.foodapp.model.Meal.MealDetails;
import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private final static String TAG = "SearchAdapter" ;
    Context context;
    CircleImageView photo;

    private List<MealDetails> listOfsearchMeals;
    private List<MealDetails> originalList;

    private boolean isFiltered;


    public SearchAdapter(Context context, List<MealDetails> listOfsearchMeals) {
        this.context = context;
        this.listOfsearchMeals = listOfsearchMeals;

    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.test,parent,false);
         SearchViewHolder searchViewHolder = new SearchViewHolder(view);
        Log.i(TAG,"<<<<<<< OnCreateViewHolder : BeefMeals >>>>>>>>>");
        return searchViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        MealDetails mealDetails =  listOfsearchMeals.get(position);
        holder.textTitle.setText(mealDetails.getStrMeal());
        Glide.with(context)
                .load(listOfsearchMeals.get(position).getStrMealThumb())
                .into(photo);


    }

    @Override
    public int getItemCount() {
        return listOfsearchMeals.size();
    }

    public void setListOfsearchMeals(List<MealDetails> listOfsearchMeals) {
        this.listOfsearchMeals = listOfsearchMeals;
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitle;
        private Button insertFav;
        FrameLayout frameLayout;

        public View layout;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.catname);
            photo = itemView.findViewById(R.id.countryImg);
            insertFav = itemView.findViewById(R.id.countryName);
            frameLayout = itemView.findViewById(R.id.frameMeal);
        }
    }
}
