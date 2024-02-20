package com.example.foodapp.weekPlan.presenter;

import com.example.foodapp.model.MealPlan.MealWithDate;

public interface WeekPlanPresenter {
    void getStoredWeekMeals();

    void deleteMealsFromWeekPlan(MealWithDate mealWithDate);
}
