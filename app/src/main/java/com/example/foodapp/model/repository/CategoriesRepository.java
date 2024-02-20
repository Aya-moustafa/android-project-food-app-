package com.example.foodapp.model.repository;

import com.example.foodapp.model.Meal.MealDetails;
import com.example.foodapp.model.MealPlan.MealWithDate;
import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;
import com.example.foodapp.network.HomeCallBack;
import com.example.foodapp.network.MealsCallBack;
import com.example.foodapp.network.MealDetailsCallBack;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface CategoriesRepository {
    void getAllCategories(HomeCallBack networkCallBack);

    void getDailyMeal(MealDetailsCallBack mealDetailsCallBack);

    void getAllIngrediants(HomeCallBack networkCallBack);

    void getBeefMeals (MealsCallBack networkCallBack);

    void getDesertMeals (MealsCallBack networkCallBack);

    void getLambMeals (MealsCallBack networkCallBack);

    void getMiscellaneousMeals (MealsCallBack networkCallBack);

    void getPastaMeals (MealsCallBack networkCallBack);


    void getPorkMeals (MealsCallBack networkCallBack);

    void getSeaFoodMeals (MealsCallBack networkCallBack);

    void getSideMeals (MealsCallBack networkCallBack);

    void getStarterMeals (MealsCallBack networkCallBack);

    void getVeganMeals (MealsCallBack networkCallBack);


    void getVegetarianMeals (MealsCallBack networkCallBack);


    void getBreakFastMeals (MealsCallBack networkCallBack);


    void getGoatMeals (MealsCallBack networkCallBack);

    void getChickenMeals (MealsCallBack networkCallBack);

    void getAllCountries (HomeCallBack networkCallBack);

    void getMealByName (MealDetailsCallBack mealDetailsCallBack, String name);

    Flowable<List<BeefMealsData>> getStoredMeals ();
    void insertMealsToFav (BeefMealsData beefMealsData);
    void deleteMealsFromFav (BeefMealsData beefMealsData);
   Flowable<List<MealWithDate>> getStoredMealsPlan();
    void insertMealsPlan (MealWithDate mealWithDate);
    void deleteMealsPlan (MealWithDate mealWithDate);

    void getMealsByFirstLetter (MealDetailsCallBack mealDetails , String firstletter);

    void getMealsByCategoryName (MealsCallBack mealsCallBack , String category);

    void getMealsByCountryName (MealsCallBack mealsCallBack , String country);

    void getMealsByIngrediantName (MealsCallBack mealsCallBack , String ingrediant);

    void getMealsByCategoryandName (MealDetailsCallBack mealsCallBack , String category , String name);

    public void getMealsByCountryandName(MealDetailsCallBack mealsCallBack, String country, String name);

    public void getMealsByIngrediantandName(MealDetailsCallBack mealsCallBack, String ingrediant, String name);
}
