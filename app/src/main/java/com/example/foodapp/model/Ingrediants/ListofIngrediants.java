package com.example.foodapp.model.Ingrediants;

import java.util.List;

public class ListofIngrediants {
    private List<IngrediantsData> meals;

    public ListofIngrediants(List<IngrediantsData> meals) {
        this.meals = meals;
    }

    public void setMeals(List<IngrediantsData> meals) {
        this.meals = meals;
    }

    public List<IngrediantsData> getMeals() {
        return meals;
    }
}
