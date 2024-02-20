package com.example.foodapp.model.repository;

import android.util.Log;

import com.example.foodapp.data_base.MealsLocalDataSource;
import com.example.foodapp.data_base.MealsLocalDataSourceImp;
import com.example.foodapp.data_base.MealsPlanLocalDataSource;
import com.example.foodapp.data_base.MealsPlanLocalDataSourceImp;
import com.example.foodapp.model.Meal.MealDetails;
import com.example.foodapp.model.MealPlan.MealWithDate;
import com.example.foodapp.model.all_category.beef_meals.BeefMealsData;
import com.example.foodapp.network.HomeCallBack;
import com.example.foodapp.network.MealsCallBack;
import com.example.foodapp.network.MealDetailsCallBack;
import com.example.foodapp.network.InGenericRemoteDataSource;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CategoriesRepositoryImp implements CategoriesRepository {

    private InGenericRemoteDataSource remoteDataSource;
    private MealsLocalDataSource localsource;
    private MealsPlanLocalDataSource planLocalDataSource;
    private static CategoriesRepositoryImp repo = null;


    private CategoriesRepositoryImp(InGenericRemoteDataSource remoteDataSource , MealsLocalDataSourceImp localsource , MealsPlanLocalDataSourceImp planLocalDataSource) {
        this.localsource = localsource;
        this.remoteDataSource = remoteDataSource;
        this.planLocalDataSource = planLocalDataSource; // Initialize planLocalDataSource

    }

    private CategoriesRepositoryImp( MealsLocalDataSourceImp localsource , MealsPlanLocalDataSourceImp planLocalDataSource) {
        this.localsource = localsource;
        this.planLocalDataSource = planLocalDataSource; // Initialize planLocalDataSource

    }


    public static CategoriesRepositoryImp getInstance(InGenericRemoteDataSource remoteDataSource ,MealsLocalDataSourceImp localsource, MealsPlanLocalDataSourceImp planLocalDataSource) {
        if(repo == null){
            repo = new CategoriesRepositoryImp(remoteDataSource , localsource,planLocalDataSource);
        }
        return repo;
    }


    public static CategoriesRepositoryImp getInstance(MealsLocalDataSourceImp localsource, MealsPlanLocalDataSourceImp planLocalDataSource) {
        if(repo == null){
            repo = new CategoriesRepositoryImp(localsource,planLocalDataSource);
        }
        return repo;
    }

    @Override
    public void getAllCategories(HomeCallBack networkCallBack) {
        remoteDataSource.MakeNetworkCall(networkCallBack);
    }

    @Override
    public void getDailyMeal(MealDetailsCallBack mealDetailsCallBack) {
        remoteDataSource.getDailyMeal(mealDetailsCallBack);
    }

    @Override
    public void getAllIngrediants(HomeCallBack networkCallBack) {
        remoteDataSource.getAllIngrediants(networkCallBack);
    }

    @Override
    public void getBeefMeals(MealsCallBack beefNetwprkCallBack) {
        remoteDataSource.getMeatMealsCall(beefNetwprkCallBack);
    }

    @Override
    public void getDesertMeals(MealsCallBack networkCallBack) {
        remoteDataSource.getDessertCall(networkCallBack);
    }

    @Override
    public void getLambMeals(MealsCallBack networkCallBack) {
        remoteDataSource.getLambtCall(networkCallBack);
    }

    @Override
    public void getMiscellaneousMeals(MealsCallBack networkCallBack) {
        remoteDataSource.getMiscellaneoustCall(networkCallBack);
    }

    @Override
    public void getPastaMeals(MealsCallBack networkCallBack) {
        remoteDataSource.getPastatCall(networkCallBack);
    }

    @Override
    public void getPorkMeals(MealsCallBack networkCallBack) {
        remoteDataSource.getPorktCall(networkCallBack);
    }

    @Override
    public void getSeaFoodMeals(MealsCallBack networkCallBack) {
        remoteDataSource.getSeaFoodtCall(networkCallBack);
    }

    @Override
    public void getSideMeals(MealsCallBack networkCallBack) {
        remoteDataSource.getSideCall(networkCallBack);
    }

    @Override
    public void getStarterMeals(MealsCallBack networkCallBack) {
        remoteDataSource.getStarterCall(networkCallBack);
    }

    @Override
    public void getVeganMeals(MealsCallBack networkCallBack) {
        remoteDataSource.getVeganCall(networkCallBack);
    }

    @Override
    public void getVegetarianMeals(MealsCallBack networkCallBack) {
        remoteDataSource.getVegetarianCall(networkCallBack);
    }

    @Override
    public void getBreakFastMeals(MealsCallBack networkCallBack) {
        remoteDataSource.getBreakFastCall(networkCallBack);
    }

    @Override
    public void getGoatMeals(MealsCallBack networkCallBack) {
        remoteDataSource.getGoatCall(networkCallBack);
    }

    @Override
    public void getChickenMeals(MealsCallBack chickennetworkCallBack) {
        remoteDataSource.getChickenCall(chickennetworkCallBack);
    }


    @Override
    public void getAllCountries(HomeCallBack countriesnetworkCallBack) {
        remoteDataSource.getCountriesCall(countriesnetworkCallBack);
    }

    @Override
    public void getMealByName(MealDetailsCallBack mealDetailsCallBack, String name) {
        remoteDataSource.getMealDetailsByName(name , mealDetailsCallBack);
    }

    @Override
    public void insertMealsToFav(BeefMealsData beefMealsData) {
       localsource.insertMeals(beefMealsData);
    }

    @Override
    public void deleteMealsFromFav(BeefMealsData beefMealsData) {
        localsource.deleteMeals(beefMealsData);
    }

    @Override
    public Flowable<List<MealWithDate>> getStoredMealsPlan() {
        return
        planLocalDataSource.getMealsPlan();
    }

    @Override
    public void insertMealsPlan(MealWithDate mealWithDate) {
           planLocalDataSource.insertMealsinPlan(mealWithDate)
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(
                           ()->{
                               Log.i("TAG", "insertMealsPlan: successfuly");
                           } ,Throwable -> {
                               Log.i("TAG", "insertMealsPlan: onFailure"+Throwable);
                           }
                   );
           ;
    }
    @Override
    public void deleteMealsPlan(MealWithDate mealWithDate) {
         planLocalDataSource.deleteMealsFromPlan(mealWithDate)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(
                         () -> {
                             Log.i("TAG", "deleteMealsPlan: successfuly");
                         } , Throwable -> {
                             Log.i("TAG", "deleteMealsPlan: onFailure" + Throwable);
                         }
                 );
    }

    @Override
    public void getMealsByFirstLetter(MealDetailsCallBack mealDetails, String firstletter) {
        remoteDataSource.searchByFirstLetter(mealDetails , firstletter);
    }

    @Override
    public void getMealsByCategoryName(MealsCallBack mealsCallBack, String category) {
        remoteDataSource.getMealsByCategoryName(mealsCallBack , category);
    }

    @Override
    public void getMealsByCountryName(MealsCallBack mealsCallBack, String country) {
        remoteDataSource.getMealsByCountryName(mealsCallBack , country);
    }

    @Override
    public void getMealsByIngrediantName(MealsCallBack mealsCallBack, String ingrediant) {
        remoteDataSource.getMealsByIngrediantName(mealsCallBack , ingrediant);

    }

    @Override
    public void getMealsByCategoryandName(MealDetailsCallBack mealsCallBack, String category, String name) {
        remoteDataSource.searchMealsByCategoryandName(name , category ,mealsCallBack);
    }

    public void getMealsByCountryandName(MealDetailsCallBack mealsCallBack, String country, String name) {
        remoteDataSource.searchMealsByCountryandName(name , country ,mealsCallBack);
    }

    public void getMealsByIngrediantandName(MealDetailsCallBack mealsCallBack, String ingrediant, String name) {
        remoteDataSource.searchMealsByIngrediantandName(name , ingrediant ,mealsCallBack);
    }

    @Override
    public Flowable<List<BeefMealsData>> getStoredMeals() {
        return localsource.getFavMeals();
    }


}
