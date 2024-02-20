package com.example.foodapp.home_page.get_all_categories.view;

import com.example.foodapp.model.Ingrediants.IngrediantsData;
import com.example.foodapp.model.Meal.MealDetails;
import com.example.foodapp.model.all_category.AllCategory;
import com.example.foodapp.model.countries.AllCountries;

import java.util.List;

public interface AllCategoriesView {
    public void showData (List<AllCategory> categoriesList) ;
    public void showCountriesData (List<AllCountries> countriesList);

    public void showIngrediantsData (List<IngrediantsData> IngrediantsList);

    public void showDailyMeal (List<MealDetails> dailyMeal);
    public void showErrorMsg (String error) ;
}
