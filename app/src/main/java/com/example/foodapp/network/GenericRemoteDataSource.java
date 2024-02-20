package com.example.foodapp.network;

import android.util.Log;

import com.example.foodapp.model.Ingrediants.IngrediantsData;
import com.example.foodapp.model.Ingrediants.ListofIngrediants;
import com.example.foodapp.model.Meal.ListMealDetails;
import com.example.foodapp.model.Meal.MealDetails;
import com.example.foodapp.model.all_category.AllCategory;
import com.example.foodapp.model.all_category.ListCategories;
import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;
import com.example.foodapp.model.all_category.beef_meals.ListBeefMeals;
import com.example.foodapp.model.countries.ListOfCountries;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GenericRemoteDataSource implements InGenericRemoteDataSource {

     List<AllCategory> categories;
     List<MealDetails> mealDetails;

     List<IngrediantsData> ingrediantsData;

     List<BeefMealsData> listMealsByCategory;
    private final static String TAG = "AllCategory";
    private final static String URL_BASE = "https://www.themealdb.com/api/json/v1/1/";

    private GenericService apiServices;

    private static GenericRemoteDataSource client = null;


    private GenericRemoteDataSource() {
        //Singlton need one instance of the Retrosit
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(URL_BASE)
                .build();

        apiServices = retrofit.create(GenericService.class);
    }

    public static GenericRemoteDataSource getInstance() {
        if(client == null) {
            client = new GenericRemoteDataSource();
        }
        return client;
    }

    @Override
    public void MakeNetworkCall(HomeCallBack networkCallBack) {
        Single<ListCategories> observable = apiServices.getAllCategories();
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                         category -> {
                             categories = category.getProducts();
                             Log.i(TAG, "onResponseCategory : " + categories);
                             networkCallBack.onSuccessResult(categories);
                         }, error -> {
                            Log.i(TAG, "onErrorCategory : " + error.getMessage());
                        }
                );
    }

    @Override
    public void getDailyMeal(MealDetailsCallBack mealDetailsCallBack) {
        Single<ListMealDetails> observable = apiServices.getRandomMeal();
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        dailyMeal -> {
                            mealDetails = dailyMeal.getMealDetails();
                            Log.i(TAG, "onResponseCategory : " + mealDetails);
                            mealDetailsCallBack.onSuccessResultMealDetails(mealDetails);
                        }, error -> {
                            Log.i(TAG, "onErrorCategory : " + error.getMessage());
                        }
                );
    }

    @Override
    public void getAllIngrediants(HomeCallBack networkCallBack) {
        Single<ListofIngrediants> observable = apiServices.getAllIngrediants();
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        ingrediants -> {
                            ingrediantsData = ingrediants.getMeals();
                            Log.i(TAG, "onResponseIngrediants : " + ingrediantsData);
                            networkCallBack.onSuccessResultIngr(ingrediantsData);
                        }, error -> {
                            Log.i(TAG, "onErrorCategory : " + error.getMessage());
                        }
                );

    }

    @Override
    public void
    getMeatMealsCall(MealsCallBack networkCallBack) {
       Call<ListBeefMeals> call = apiServices.getBeefMeals();
        call.enqueue(new Callback<ListBeefMeals>() {
            @Override
            public void onResponse(Call<ListBeefMeals> call, Response<ListBeefMeals> response) {
                if(response.isSuccessful() && response.body() != null){
                    Log.i(TAG,"<<<<<<<<<<<<<<<<<<BeefMeals Response >>>>>>>>>>>>>>>>>>>>>: " + response.body().meals);
                    networkCallBack.onSuccessResultMeal(response.body().meals);
                }
            }
            @Override
            public void onFailure(Call<ListBeefMeals> call, Throwable t) {
                Log.i(TAG,"<<<<<<<<<<<<<<BeefMeals onFailure>>>>>>>>>>>>>>>> : "+t.getMessage());
                networkCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void getChickenCall(MealsCallBack networkCallBack) {
        Call<ListBeefMeals> call = apiServices.getChickenMeals();
        call.enqueue(new Callback<ListBeefMeals>() {
            @Override
            public void onResponse(Call<ListBeefMeals> call, Response<ListBeefMeals> response) {
                if(response.isSuccessful() && response.body() != null){
                    networkCallBack.onSuccessResultMeal(response.body().meals);
                    Log.i(TAG,"<<<<<<<<<<<<<<<<<<ChickenMeals Response >>>>>>>>>>>>>>>>>>>>>: " + response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<ListBeefMeals> call, Throwable t) {
                Log.e(TAG,"<<<<<<<<<<<<<<ChickenMeals onFailure>>>>>>>>>>>>>>>>"+t.getMessage());
                networkCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void getDessertCall(MealsCallBack networkCallBack) {
        Call<ListBeefMeals> call = apiServices.getDessertMeals();
        call.enqueue(new Callback<ListBeefMeals>() {
            @Override
            public void onResponse(Call<ListBeefMeals> call, Response<ListBeefMeals> response) {
                if(response.isSuccessful() && response.body() != null){
                    networkCallBack.onSuccessResultMeal(response.body().meals);
                    Log.i(TAG,"<<<<<<<<<<<<<<<<<<Dessert Response >>>>>>>>>>>>>>>>>>>>>: " + response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<ListBeefMeals> call, Throwable t) {
                Log.e(TAG,"<<<<<<<<<<<<<<Dessert onFailure>>>>>>>>>>>>>>>>"+t.getMessage());
                networkCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void getLambtCall(MealsCallBack networkCallBack) {
        Call<ListBeefMeals> call = apiServices.getLambMeals();
        call.enqueue(new Callback<ListBeefMeals>() {
            @Override
            public void onResponse(Call<ListBeefMeals> call, Response<ListBeefMeals> response) {
                if(response.isSuccessful() && response.body() != null){
                    networkCallBack.onSuccessResultMeal(response.body().meals);
                    Log.i(TAG,"<<<<<<<<<<<<<<<<<<Lamb Response >>>>>>>>>>>>>>>>>>>>>: " + response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<ListBeefMeals> call, Throwable t) {
                Log.e(TAG,"<<<<<<<<<<<<<<Lamb onFailure>>>>>>>>>>>>>>>>"+t.getMessage());
                networkCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void getMiscellaneoustCall(MealsCallBack networkCallBack) {
        Call<ListBeefMeals> call = apiServices.getMiscellanMeals();
        call.enqueue(new Callback<ListBeefMeals>() {
            @Override
            public void onResponse(Call<ListBeefMeals> call, Response<ListBeefMeals> response) {
                if(response.isSuccessful() && response.body() != null){
                    networkCallBack.onSuccessResultMeal(response.body().meals);
                    Log.i(TAG,"<<<<<<<<<<<<<<<<<<MiscellanMeals Response >>>>>>>>>>>>>>>>>>>>>: " + response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<ListBeefMeals> call, Throwable t) {
                Log.e(TAG,"<<<<<<<<<<<<<<MiscellanMeals onFailure>>>>>>>>>>>>>>>>"+t.getMessage());
                networkCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void getPastatCall(MealsCallBack networkCallBack) {
        Call<ListBeefMeals> call = apiServices.getPastaMeals();
        call.enqueue(new Callback<ListBeefMeals>() {
            @Override
            public void onResponse(Call<ListBeefMeals> call, Response<ListBeefMeals> response) {
                if(response.isSuccessful() && response.body() != null){
                    networkCallBack.onSuccessResultMeal(response.body().meals);
                    Log.i(TAG,"<<<<<<<<<<<<<<<<<<Pasta Response >>>>>>>>>>>>>>>>>>>>>: " + response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<ListBeefMeals> call, Throwable t) {
                Log.e(TAG,"<<<<<<<<<<<<<<Pasta onFailure>>>>>>>>>>>>>>>>"+t.getMessage());
                networkCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void getPorktCall(MealsCallBack networkCallBack) {
        Call<ListBeefMeals> call = apiServices.getPorkMeals();
        call.enqueue(new Callback<ListBeefMeals>() {
            @Override
            public void onResponse(Call<ListBeefMeals> call, Response<ListBeefMeals> response) {
                if(response.isSuccessful() && response.body() != null){
                    networkCallBack.onSuccessResultMeal(response.body().meals);
                    Log.i(TAG,"<<<<<<<<<<<<<<<<<<Pork Response >>>>>>>>>>>>>>>>>>>>>: " + response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<ListBeefMeals> call, Throwable t) {
                Log.e(TAG,"<<<<<<<<<<<<<<Pork onFailure>>>>>>>>>>>>>>>>"+t.getMessage());
                networkCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void getSeaFoodtCall(MealsCallBack networkCallBack) {
        Call<ListBeefMeals> call = apiServices.getSeaFoodMeals();
        call.enqueue(new Callback<ListBeefMeals>() {
            @Override
            public void onResponse(Call<ListBeefMeals> call, Response<ListBeefMeals> response) {
                if(response.isSuccessful() && response.body() != null){
                    networkCallBack.onSuccessResultMeal(response.body().meals);
                    Log.i(TAG,"<<<<<<<<<<<<<<<<<<SeaFood Response >>>>>>>>>>>>>>>>>>>>>: " + response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<ListBeefMeals> call, Throwable t) {
                Log.e(TAG,"<<<<<<<<<<<<<<SeaFood onFailure>>>>>>>>>>>>>>>>"+t.getMessage());
                networkCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void getSideCall(MealsCallBack networkCallBack) {
        Call<ListBeefMeals> call = apiServices.getSideMeals();
        call.enqueue(new Callback<ListBeefMeals>() {
            @Override
            public void onResponse(Call<ListBeefMeals> call, Response<ListBeefMeals> response) {
                if(response.isSuccessful() && response.body() != null){
                    networkCallBack.onSuccessResultMeal(response.body().meals);
                    Log.i(TAG,"<<<<<<<<<<<<<<<<<<Side Response >>>>>>>>>>>>>>>>>>>>>: " + response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<ListBeefMeals> call, Throwable t) {
                Log.e(TAG,"<<<<<<<<<<<<<<Side onFailure>>>>>>>>>>>>>>>>"+t.getMessage());
                networkCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void getStarterCall(MealsCallBack networkCallBack) {
        Call<ListBeefMeals> call = apiServices.getStarterMeals();
        call.enqueue(new Callback<ListBeefMeals>() {
            @Override
            public void onResponse(Call<ListBeefMeals> call, Response<ListBeefMeals> response) {
                if(response.isSuccessful() && response.body() != null){
                    networkCallBack.onSuccessResultMeal(response.body().meals);
                    Log.i(TAG,"<<<<<<<<<<<<<<<<<<Starter Response >>>>>>>>>>>>>>>>>>>>>: " + response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<ListBeefMeals> call, Throwable t) {
                Log.e(TAG,"<<<<<<<<<<<<<<Starter onFailure>>>>>>>>>>>>>>>>"+t.getMessage());
                networkCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void getVeganCall(MealsCallBack networkCallBack) {
        Call<ListBeefMeals> call = apiServices.getVeganMeals();
        call.enqueue(new Callback<ListBeefMeals>() {
            @Override
            public void onResponse(Call<ListBeefMeals> call, Response<ListBeefMeals> response) {
                if(response.isSuccessful() && response.body() != null){
                    networkCallBack.onSuccessResultMeal(response.body().meals);
                    Log.i(TAG,"<<<<<<<<<<<<<<<<<<Vegan Response >>>>>>>>>>>>>>>>>>>>>: " + response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<ListBeefMeals> call, Throwable t) {
                Log.e(TAG,"<<<<<<<<<<<<<<Vegan onFailure>>>>>>>>>>>>>>>>"+t.getMessage());
                networkCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void getVegetarianCall(MealsCallBack networkCallBack) {
        Call<ListBeefMeals> call = apiServices.getVegetarianMeals();
        call.enqueue(new Callback<ListBeefMeals>() {
            @Override
            public void onResponse(Call<ListBeefMeals> call, Response<ListBeefMeals> response) {
                if(response.isSuccessful() && response.body() != null){
                    networkCallBack.onSuccessResultMeal(response.body().meals);
                    Log.i(TAG,"<<<<<<<<<<<<<<<<<<Vegetarian Response >>>>>>>>>>>>>>>>>>>>>: " + response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<ListBeefMeals> call, Throwable t) {
                Log.e(TAG,"<<<<<<<<<<<<<<Vegetarian onFailure>>>>>>>>>>>>>>>>"+t.getMessage());
                networkCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void getBreakFastCall(MealsCallBack networkCallBack) {
        Call<ListBeefMeals> call = apiServices.getBreakFastMeals();
        call.enqueue(new Callback<ListBeefMeals>() {
            @Override
            public void onResponse(Call<ListBeefMeals> call, Response<ListBeefMeals> response) {
                if(response.isSuccessful() && response.body() != null){
                    networkCallBack.onSuccessResultMeal(response.body().meals);
                    Log.i(TAG,"<<<<<<<<<<<<<<<<<<BreakFast Response >>>>>>>>>>>>>>>>>>>>>: " + response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<ListBeefMeals> call, Throwable t) {
                Log.e(TAG,"<<<<<<<<<<<<<<BreakFast onFailure>>>>>>>>>>>>>>>>"+t.getMessage());
                networkCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void getGoatCall(MealsCallBack networkCallBack) {
        Call<ListBeefMeals> call = apiServices.getGoatMeals();
        call.enqueue(new Callback<ListBeefMeals>() {
            @Override
            public void onResponse(Call<ListBeefMeals> call, Response<ListBeefMeals> response) {
                if(response.isSuccessful() && response.body() != null){
                    networkCallBack.onSuccessResultMeal(response.body().meals);
                    Log.i(TAG,"<<<<<<<<<<<<<<<<<<Goat Response >>>>>>>>>>>>>>>>>>>>>: " + response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<ListBeefMeals> call, Throwable t) {
                Log.e(TAG,"<<<<<<<<<<<<<<Goat onFailure>>>>>>>>>>>>>>>>"+t.getMessage());
                networkCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void getCountriesCall(HomeCallBack networkCallBack) {
       Call<ListOfCountries> call = apiServices.getAllCountries();
       call.enqueue(new Callback<ListOfCountries>() {
           @Override
           public void onResponse(Call<ListOfCountries> call, Response<ListOfCountries> response) {
               if(response.isSuccessful() && response.body() != null){
                   networkCallBack.onSuccessResultCon(response.body().meals);
                   Log.i(TAG,"<<<<<<<<<<<<<<<<<<AllCountries Response >>>>>>>>>>>>>>>>>>>>>: " + response.body().meals);
               }
           }

           @Override
           public void onFailure(Call<ListOfCountries> call, Throwable t) {
               Log.e(TAG,"<<<<<<<<<<<<<<Countries onFailure>>>>>>>>>>>>>>>>"+t.getMessage());
               networkCallBack.onFailureResult(t.getMessage());
               t.printStackTrace();
           }
       });
    }

    @Override
    public void getMealDetailsByName(String mealName, MealDetailsCallBack mealDetailsCallBack) {
        Single<ListMealDetails> observable = apiServices.getMealByName(mealName);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                         listMealsDetails ->{
                                mealDetails = listMealsDetails.getMealDetails();
                                mealDetailsCallBack.onSuccessResultMealDetails(mealDetails);
                                Log.i(TAG, "getMealDetailsByName: "+mealDetails);
                         }, error -> {
                            Log.i(TAG, "getMealDetailsByName: OnError"+error.getMessage());
                        }
                );
    }

    @Override
    public  void searchMealsByCategoryandName (String mealName , String categoryName ,  MealDetailsCallBack mealDetailsCallBack) {
        Single<ListMealDetails> observable = apiServices.getMealByName(mealName);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        listMealsDetails ->{
                            List<MealDetails> filteredMeals = filterMealsByCategory(listMealsDetails.getMealDetails(), categoryName);
                            mealDetailsCallBack.onSuccessResultMealDetails(filteredMeals);
                            Log.i(TAG, "getMealDetailsByNameAndCategory: "+filteredMeals);
                        }, error -> {
                            Log.i(TAG, "getMealDetailsByNameAndCategory: OnError"+error.getMessage());
                        }
                );
    }

    @Override
    public  void searchMealsByCountryandName (String mealName , String countryName ,  MealDetailsCallBack mealDetailsCallBack) {
        Single<ListMealDetails> observable = apiServices.getMealByName(mealName);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        listMealsDetails ->{
                            List<MealDetails> filteredMeals = filterMealsByCountry(listMealsDetails.getMealDetails(), countryName);
                            mealDetailsCallBack.onSuccessResultMealDetails(filteredMeals);
                            Log.i(TAG, "getMealDetailsByNameAndCountry: "+filteredMeals);
                        }, error -> {
                            Log.i(TAG, "getMealDetailsByNameAndCountry: OnError"+error.getMessage());
                        }
                );
    }
@Override
    public  void searchMealsByIngrediantandName (String mealName , String ingrediant ,  MealDetailsCallBack mealDetailsCallBack) {
        Single<ListMealDetails> observable = apiServices.getMealByName(mealName);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        listMealsDetails ->{
                            List<MealDetails> filteredMeals = filterMealsByIngrediant(listMealsDetails.getMealDetails(), ingrediant);
                            mealDetailsCallBack.onSuccessResultMealDetails(filteredMeals);
                            Log.i(TAG, "getMealDetailsByNameAndCountry: "+filteredMeals);
                        }, error -> {
                            Log.i(TAG, "getMealDetailsByNameAndCountry: OnError"+error.getMessage());
                        }
                );
    }

    private List<MealDetails> filterMealsByIngrediant(List<MealDetails> mealDetails, String ingrediant) {
        List<MealDetails> filteredMeals = new ArrayList<>();
        for (MealDetails meal : mealDetails) {
            // Check if the meal belongs to the selected category
            if (meal.getStrIngredient1().equalsIgnoreCase(ingrediant) || meal.getStrIngredient2().equalsIgnoreCase(ingrediant) || meal.getStrIngredient3().equalsIgnoreCase(ingrediant)
            ||meal.getStrIngredient4().equalsIgnoreCase(ingrediant) || meal.getStrIngredient5().equalsIgnoreCase(ingrediant) || meal.getStrIngredient6().equalsIgnoreCase(ingrediant) || meal.getStrIngredient7().equalsIgnoreCase(ingrediant)
            || meal.getStrIngredient8().equalsIgnoreCase(ingrediant) || meal.getStrIngredient9().equalsIgnoreCase(ingrediant) || meal.getStrIngredient10().equalsIgnoreCase(ingrediant)) {
                filteredMeals.add(meal);
            }
        }
        return filteredMeals;
    }

    private List<MealDetails> filterMealsByCountry(List<MealDetails> mealDetails, String countryName) {
        List<MealDetails> filteredMeals = new ArrayList<>();
        for (MealDetails meal : mealDetails) {
            // Check if the meal belongs to the selected category
            if (meal.getStrArea().equalsIgnoreCase(countryName)) {
                filteredMeals.add(meal);
            }
        }
        return filteredMeals;
    }

    private List<MealDetails> filterMealsByCategory(List<MealDetails> mealDetails, String categoryName) {
        List<MealDetails> filteredMeals = new ArrayList<>();
        for (MealDetails meal : mealDetails) {
            // Check if the meal belongs to the selected category
            if (meal.getStrCategory().equalsIgnoreCase(categoryName)) {
                filteredMeals.add(meal);
            }
        }
        return filteredMeals;
    }

    @Override
    public void searchByFirstLetter(MealDetailsCallBack mealDetailsCallBack , String firstLetter ) {
        Single<ListMealDetails> observable = apiServices.searchMealByFirstLetter(firstLetter);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        listMeals -> {
                            mealDetails = listMeals.getMealDetails();
                            mealDetailsCallBack.onSuccessResultMealDetails(mealDetails);
                            Log.i(TAG, "searchByFirstName Success Return Data: "+mealDetails);
                        }, error -> {
                            Log.i(TAG, "searchByFirstName: OnError"+error.getMessage());
                        }
                );
    }

    @Override
    public void getMealsByCategoryName(MealsCallBack mealsCallBack, String Category) {
        Single<ListBeefMeals> observable = apiServices.getMealsByCategory(Category);

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        listMeals -> {
                            listMealsByCategory = listMeals.getMeals();
                            mealsCallBack.onSuccessResultMeal(listMealsByCategory);
                            Log.i(TAG, "getMealsByCategoryName: Success return data"+listMealsByCategory);
                        } , error -> {
                            Log.i(TAG, "getMealsByCategoryName: onError"+error.getMessage());
                        }
                );
    }

    @Override
    public void getMealsByCountryName(MealsCallBack mealsCallBack, String Country) {
        Single<ListBeefMeals> observable = apiServices.getMealsByCountry(Country);

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        listMeals -> {
                            listMealsByCategory = listMeals.getMeals();
                            mealsCallBack.onSuccessResultMeal(listMealsByCategory);
                            Log.i(TAG, "getMealsByCategoryName: Success return data"+listMealsByCategory);
                        } , error -> {
                            Log.i(TAG, "getMealsByCategoryName: onError"+error.getMessage());
                        }
                );
    }

    @Override
    public void getMealsByIngrediantName(MealsCallBack mealsCallBack, String Ingrediant) {
        Single<ListBeefMeals> observable = apiServices.getMealsByIngrediant(Ingrediant);

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        listMeals -> {
                            listMealsByCategory = listMeals.getMeals();
                            mealsCallBack.onSuccessResultMeal(listMealsByCategory);
                            Log.i(TAG, "getMealsByCategoryName: Success return data"+listMealsByCategory);
                        } , error -> {
                            Log.i(TAG, "getMealsByCategoryName: onError"+error.getMessage());
                        }
                );
    }


}
