package com.example.foodapp.meals.presenter;

import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;

public interface MealsPresenter {
    void getMeals();
    void getChicken();

    void getDessert();

    void getLamb();

    void getMiscellaneous();

    void getPasta();

    void getPork();

    void getSeaFood();

    void getSide();

    void getStarter();

    void getVegan();

    void getVegetarian();

    void getBreakFast();

    void getGoat();
    void addToFav(BeefMealsData beef);

    void getmealsByCountry(String countryname);
}
