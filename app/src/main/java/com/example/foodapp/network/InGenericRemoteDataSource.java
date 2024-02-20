package com.example.foodapp.network;


public interface InGenericRemoteDataSource {
    void MakeNetworkCall(HomeCallBack networkCallBack);

    void getDailyMeal (MealDetailsCallBack mealDetailsCallBack);

    void getAllIngrediants(HomeCallBack networkCallBack);

    void getMeatMealsCall(MealsCallBack networkCallBack);

    void getChickenCall(MealsCallBack networkCallBack);

    void getDessertCall(MealsCallBack networkCallBack);

    void getLambtCall(MealsCallBack networkCallBack);

    void getMiscellaneoustCall(MealsCallBack networkCallBack);

    void getPastatCall(MealsCallBack networkCallBack);

    void getPorktCall(MealsCallBack networkCallBack);

    void getSeaFoodtCall(MealsCallBack networkCallBack);

    void getSideCall(MealsCallBack networkCallBack);

    void getStarterCall(MealsCallBack networkCallBack);

    void getVeganCall(MealsCallBack networkCallBack);

    void getVegetarianCall(MealsCallBack networkCallBack);

    void getBreakFastCall(MealsCallBack networkCallBack);

    void getGoatCall(MealsCallBack networkCallBack);

    void getCountriesCall(HomeCallBack networkCallBack);

    void getMealDetailsByName (String mealName , MealDetailsCallBack mealDetailsCallBack);

    void searchByFirstLetter (MealDetailsCallBack mealDetailsCallBack , String firstLetter );

    void getMealsByCategoryName (MealsCallBack mealsCallBack, String Category);

    void getMealsByCountryName (MealsCallBack mealsCallBack, String Country);

    void getMealsByIngrediantName (MealsCallBack mealsCallBack, String Ingrediant);

    public  void searchMealsByCategoryandName (String mealName , String categoryName ,  MealDetailsCallBack mealDetailsCallBack);

    public void searchMealsByIngrediantandName (String mealName , String ingrediant ,  MealDetailsCallBack mealDetailsCallBack) ;
    public void searchMealsByCountryandName (String mealName , String countryName ,  MealDetailsCallBack mealDetailsCallBack);

}
