package com.example.foodapp.search.presenter;

import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;

public interface SearchPresenter {
    void getSearchMealsByFirstLetter(String firstLetter);
    void getSearchMealsByFirstName (String mealName);
    void getMealsByCategoryName(String categoryName);
    void getMealsByCountryName(String countryName);
    void getMealsByIngrediantName(String ingrediantName);

    void getMealsBycategoryAndname(String name , String category);

    void getMealsBycountryAndname(String name , String country);

    void getMealsByingrediantAndname(String name , String ingrediant);

    void addToFav(BeefMealsData beef);

    void getAllcountries();
    void getAllcategories();
    void getAllIngrediants();
}
