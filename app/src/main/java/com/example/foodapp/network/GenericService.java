package com.example.foodapp.network;

import com.example.foodapp.model.Ingrediants.ListofIngrediants;
import com.example.foodapp.model.Meal.ListMealDetails;
import com.example.foodapp.model.Meal.MealDetails;
import com.example.foodapp.model.all_category.ListCategories;
import com.example.foodapp.model.all_category.beef_meals.ListBeefMeals;
import com.example.foodapp.model.countries.ListOfCountries;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GenericService {
    @GET("categories.php")
    Single<ListCategories> getAllCategories();

    @GET("list.php?i=list")
    Single<ListofIngrediants> getAllIngrediants();
    @GET("filter.php?c=Beef")
    Call<ListBeefMeals> getBeefMeals();

    @GET("filter.php?c=Chicken")
    Call<ListBeefMeals> getChickenMeals();

    @GET("filter.php?c=Dessert")
    Call<ListBeefMeals> getDessertMeals();

    @GET("filter.php?c=Lamb")
    Call<ListBeefMeals> getLambMeals();

    @GET("filter.php?c=Miscellaneous")
    Call<ListBeefMeals> getMiscellanMeals();

    @GET("filter.php?c=Pasta")
    Call<ListBeefMeals> getPastaMeals();

    @GET("filter.php?c=Pork")
    Call<ListBeefMeals> getPorkMeals();

    @GET("filter.php?c=SeaFood")
    Call<ListBeefMeals> getSeaFoodMeals();

    @GET("filter.php?c=Side")
    Call<ListBeefMeals> getSideMeals();


    @GET("filter.php?c=Starter")
    Call<ListBeefMeals> getStarterMeals();


    @GET("filter.php?c=Vegan")
    Call<ListBeefMeals> getVeganMeals();

    @GET("filter.php?c=Vegetarian")
    Call<ListBeefMeals> getVegetarianMeals();

    @GET("filter.php?c=Breakfast")
    Call<ListBeefMeals> getBreakFastMeals();


    @GET("filter.php?c=Goat")
    Call<ListBeefMeals> getGoatMeals();

    @GET("list.php?a=list")
    Call<ListOfCountries> getAllCountries();

    @GET("search.php")
    Single<ListMealDetails> getMealByName(@Query("s") String mealName);

    @GET("search.php")
    Single<ListMealDetails> searchMealByFirstLetter(@Query("f") String firstLetter);

    @GET("filter.php")
    Single<ListBeefMeals> getMealsByCategory(@Query("c") String category);

    @GET("filter.php")
    Single<ListBeefMeals> getMealsByCountry(@Query("a") String country);

    @GET("filter.php")
    Single<ListBeefMeals> getMealsByIngrediant(@Query("i") String country);

    @GET("random.php")
    Single<ListMealDetails> getRandomMeal();
}
