package com.example.foodapp.meals.view;

import android.view.View;

import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;

public interface OnFavMealClickListener {
    void onClickListener (BeefMealsData beefMeal);
    void onItemClickListener (View view,BeefMealsData mealsData);
}
