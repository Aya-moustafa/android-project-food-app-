package com.example.foodapp.model.Meal;

import java.util.List;

public class ListMealDetails {
    private List<MealDetails> meals;

    public ListMealDetails(List<MealDetails> mealDetails) {
        this.meals = mealDetails;
    }

    public List<MealDetails> getMealDetails() {
        return meals;
    }

    public void setMealDetails(List<MealDetails> mealDetails) {
        this.meals = mealDetails;
    }
}
