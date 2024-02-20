package com.example.foodapp.one_meal.presenter;

import com.example.foodapp.model.Meal.MealDetails;
import com.example.foodapp.model.MealPlan.MealWithDate;
import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;

public interface SpecificMealPresenter {
    MealDetails getMealByname(String mealName);
    void addToFavMeals(BeefMealsData mealDetails);
    void addMealToPlan (MealWithDate mealWithDate);
}
