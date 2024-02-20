package com.example.foodapp.favorites.presenter;

import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;

public interface FavoriateMealsPresenter {
    void getStoredMeals();

    void deleteMealsFromFavoriate(BeefMealsData beefMealsData);
}
