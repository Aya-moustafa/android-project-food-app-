package com.example.foodapp.model.all_category.beef_meals;

import java.util.List;

public class ListBeefMeals {

    public List<BeefMealsData> meals;

    public ListBeefMeals (){

    }

    public ListBeefMeals(List<BeefMealsData> meals) {
        this.meals = meals;
    }

    public void setMeals(List<BeefMealsData> meals) {
        this.meals = meals;
    }

    public List<BeefMealsData> getMeals() {
        return meals;
    }
}
