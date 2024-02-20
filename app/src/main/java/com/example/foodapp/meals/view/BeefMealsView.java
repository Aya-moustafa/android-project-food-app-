package com.example.foodapp.meals.view;

import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;

import java.util.List;

public interface BeefMealsView {
    public void showMeals (List<BeefMealsData> beefMealsList);
    public void showErrormsg (String error);
}
